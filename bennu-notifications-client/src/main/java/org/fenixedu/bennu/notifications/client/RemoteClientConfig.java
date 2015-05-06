package org.fenixedu.bennu.notifications.client;

public class RemoteClientConfig {

    private String url;
    private static RemoteClientConfig instance;

    private RemoteClientConfig() {
        // TODO: Get url from a configuration file or something like that
    }

    public static RemoteClientConfig getInstance() {
        if (instance == null) {
            instance = new RemoteClientConfig();
        }
        return instance;
    }

    public String getUrl() {
        return url;
    }
}
