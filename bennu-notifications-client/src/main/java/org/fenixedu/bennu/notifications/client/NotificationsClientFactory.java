package org.fenixedu.bennu.notifications.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.fenixedu.notificationscore.client.ClientType;
import org.fenixedu.notificationscore.client.NotificationsClient;

public class NotificationsClientFactory {

    private static final String LOCAL_CLIENT_CLASS = "org.fenixedu.bennu.notifications.master.client.LocalNotificationsClient";
    private static final String GET_INSTANCE = "getInstance";

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
        try {
            Class<?> clazz = Class.forName(LOCAL_CLIENT_CLASS);
            Method getInstanceMethod = clazz.getMethod(GET_INSTANCE);
            return (NotificationsClient) getInstanceMethod.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }

    private static NotificationsClient getRemoteClient() {
        return RemoteNotificationsClient.getInstance();
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
            instance = getLocalClient();
            if (instance == null) {
                instance = getRemoteClient();
            }
        }
        return instance;
    }
}
