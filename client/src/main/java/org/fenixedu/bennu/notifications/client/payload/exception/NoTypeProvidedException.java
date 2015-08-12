package org.fenixedu.bennu.notifications.client.payload.exception;

public class NoTypeProvidedException extends NotificationPayloadException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String MESSAGE = "No type was provided";

    public NoTypeProvidedException() {
        super(MESSAGE);
    }

}
