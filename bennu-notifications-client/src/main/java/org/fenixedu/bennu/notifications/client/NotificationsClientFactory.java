package org.fenixedu.bennu.notifications.client;

import java.util.ArrayList;
import java.util.List;

import org.fenixedu.notificationscore.client.ClientType;
import org.fenixedu.notificationscore.client.NotificationsClient;

public class NotificationsClientFactory {

    private static final String LOCAL_CLIENT_CLASS = "org.fenixedu.bennu.notifications.master.client.LocalNotificationsClient";

    private static NotificationsClient instance;

    private static boolean classIsPresent(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    private static NotificationsClient getLocalClient() {
        Class<? extends NotificationsClient> clazz;
        try {
            clazz = Class.forName(LOCAL_CLIENT_CLASS).asSubclass(NotificationsClient.class);
            return clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return null;
        }

    }

    private static NotificationsClient getRemoteClient() {
        return new RemoteNotificationsClient(new RemoteClientConfig());
    }

    public static boolean isLocalClientAvailable() {
        return classIsPresent(LOCAL_CLIENT_CLASS);
    }

    public static List<ClientType> getAvailableClientTypes() {
        List<ClientType> clientTypes = new ArrayList<ClientType>(ClientType.values().length);
        // Remote client is always available
        clientTypes.add(ClientType.REMOTE);
        if (isLocalClientAvailable()) {
            clientTypes.add(ClientType.LOCAL);
        }
        return clientTypes;
    }

    public static NotificationsClient getClient() {
        if (instance == null) {
            /* TODO: Get the right client based on configuration (file or admin interface)
             * if the configuration says that the client is remote and which url to use, 
             * instatiate the remote one.
             * if the configuration says that the client is local and we have the dependency of master module, 
             * instatiate the local client
             */
            instance = getLocalClient();
        }
        return instance;
    }
}
