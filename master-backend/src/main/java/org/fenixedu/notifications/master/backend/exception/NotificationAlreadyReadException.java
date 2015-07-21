package org.fenixedu.notifications.master.backend.exception;

import org.fenixedu.notifications.master.backend.NotificationInfo;

public class NotificationAlreadyReadException extends MasterBackendException {

    public NotificationAlreadyReadException(NotificationInfo notification) {
        this(notification.getId());
    }

    public NotificationAlreadyReadException(String id) {
        super("Notification " + id + " already read");
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
