package org.fenixedu.bennu.notifications.client.payload.exception;

public class NoDescriptionProvidedException extends NotificationPayloadException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "No description was provided. You need to provide, at least, one description";

    public NoDescriptionProvidedException() {
        super(MESSAGE);
    }
}
