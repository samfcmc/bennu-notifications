package org.fenixedu.bennu.notifications.master.exception;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;

public class UserNotAllowedToReadException extends MasterException {

    public UserNotAllowedToReadException(DispatchedNotification notification, User user) {
        super("User " + user.getUsername() + " is not allowed to read notification " + notification.getExternalId());
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
