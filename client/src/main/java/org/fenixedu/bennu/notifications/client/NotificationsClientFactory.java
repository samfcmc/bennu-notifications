package org.fenixedu.bennu.notifications.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NotificationsClientFactory {

    private static NotificationsClient instance;

    public static NotificationsClient getClient() {
        if (instance == null) {
            /*
             * TODO: Get the right client based on configuration file 
             */
        }
        return instance;
    }

    private static RemoteClientConfig getConfigFromPropertiesFile() throws IOException {
        Properties properties = new Properties();
        String fileName = "notifications.properties";
        InputStream inputStream = RemoteClientConfig.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException("Cannot find " + fileName + " file for the notifications module");
        } else {
            properties.load(inputStream);
            String url = properties.getProperty("url");
            String appId = properties.getProperty("appId");
            String appSecret = properties.getProperty("appSecret");
            return new RemoteClientConfig(url, appId, appSecret);
        }
    }
}
