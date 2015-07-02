package org.fenixedu.bennu.notifications.master.exception;

public class NotificationDoesNotBelongToUserException extends MasterException {

    public NotificationDoesNotBelongToUserException(String username, String notificationId) {
        super("Notification with id " + notificationId + " does not belong to user " + username);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
