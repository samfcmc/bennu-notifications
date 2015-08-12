package org.fenixedu.bennu.notifications.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.fenixedu.bennu.notifications.client.exception.ErrorReadingConfigurationFileException;
import org.fenixedu.bennu.notifications.client.exception.PropertiesFileNotFound;

public class NotificationsClientFactory {

    private static NotificationsClient instance;

    private NotificationsClientFactory() {
    }

    public static NotificationsClient getClient() {
        if (instance == null) {
            RemoteClientConfig config = getConfigFromPropertiesFile();
            instance = new RemoteNotificationsClient(config);
        }
        return instance;
    }

    private static RemoteClientConfig getConfigFromPropertiesFile() {
        Properties properties = new Properties();
        String fileName = "notifications.properties";
        InputStream inputStream = RemoteClientConfig.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new PropertiesFileNotFound(fileName);
        } else {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new ErrorReadingConfigurationFileException();
            }
            String url = properties.getProperty("url");
            String appId = properties.getProperty("appId");
            String appSecret = properties.getProperty("appSecret");
            return new RemoteClientConfig(url, appId, appSecret);
        }
    }
}
