package org.fenixedu.bennu.notifications.client.exception;

public class ErrorReadingConfigurationFileException extends NotificationsClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ErrorReadingConfigurationFileException() {
        super("Error reading notifications configuration file");
    }

}
