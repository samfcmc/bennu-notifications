package org.fenixedu.notifications.master.backend.exception;

public class NotificationDoesNotBelongToUserException extends MasterBackendException {

    public NotificationDoesNotBelongToUserException(String username, String notificationId) {
        super("Notification with id " + notificationId + " does not belong to user " + username);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
