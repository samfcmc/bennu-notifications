package org.fenixedu.bennu.notifications.master.exception;

import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;

public class NotificationAlreadReadException extends MasterException {

    public NotificationAlreadReadException(DispatchedNotification notification) {
        super("Notification " + notification.getExternalId() + " already read");
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
