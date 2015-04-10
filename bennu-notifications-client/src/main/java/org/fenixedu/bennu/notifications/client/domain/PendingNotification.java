package org.fenixedu.bennu.notifications.client.domain;

import org.fenixedu.bennu.core.domain.User;

public class PendingNotification extends PendingNotification_Base {

    public PendingNotification(User user) {
        super();
        addUser(user);
    }

}
