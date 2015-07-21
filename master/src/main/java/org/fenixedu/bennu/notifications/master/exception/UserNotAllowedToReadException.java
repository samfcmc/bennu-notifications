package org.fenixedu.bennu.notifications.master.exception;

import org.fenixedu.notifications.master.backend.NotificationInfo;

public class UserNotAllowedToReadException extends MasterException {

    public UserNotAllowedToReadException(NotificationInfo notification, String username) {
        super("User " + username + " is not allowed to read notification " + notification.getId());
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
