package org.fenixedu.bennu.notifications.client;

public class RemoteClientConfig {

    private String url;

    public RemoteClientConfig() {
        // TODO: Get url from a configuration file or something like that
    }

    public String getUrl() {
        return url;
    }

    public String getEndpointUrl(String endpoint) {
        return getUrl() + endpoint;
    }
}
