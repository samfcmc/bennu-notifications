package org.fenixedu.bennu.notifications.master.exception;

public class NotificationNotFoundException extends MasterException {

    public NotificationNotFoundException(String id) {
        super("Notification with id " + id + " not found");
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
