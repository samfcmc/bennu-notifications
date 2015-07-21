package org.fenixedu.notifications.master.backend.exception;

public class NotificationNotFoundException extends MasterBackendException {

    public NotificationNotFoundException(String id) {
        super("Notification with id " + id + " not found");
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
