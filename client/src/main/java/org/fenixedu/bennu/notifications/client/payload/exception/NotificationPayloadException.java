package org.fenixedu.bennu.notifications.client.payload.exception;

public abstract class NotificationPayloadException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NotificationPayloadException() {
        super();
    }

    public NotificationPayloadException(String message) {
        super(message);
    }

}
