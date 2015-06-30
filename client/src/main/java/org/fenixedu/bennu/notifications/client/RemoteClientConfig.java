package org.fenixedu.bennu.notifications.client;

public class RemoteClientConfig {

    private String url;
    private String appId;
    private String appSecret;

    public RemoteClientConfig(String url, String appId, String appSecret) {
        this.url = url;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public String getUrl() {
        return url;
    }

    public String getEndpointUrl(String endpoint) {
        return getUrl() + endpoint;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
