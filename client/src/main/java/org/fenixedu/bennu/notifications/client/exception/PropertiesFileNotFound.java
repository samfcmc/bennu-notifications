package org.fenixedu.bennu.notifications.client.exception;

public class PropertiesFileNotFound extends NotificationsClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String filename;

    public PropertiesFileNotFound(String filename) {
        super("Cannot find notifications configuration file " + filename);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

}
