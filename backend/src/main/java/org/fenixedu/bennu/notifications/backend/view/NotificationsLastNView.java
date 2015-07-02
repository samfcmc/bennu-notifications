package org.fenixedu.bennu.notifications.backend.view;

import org.fenixedu.bennu.core.domain.User;

public class NotificationsLastNView extends UserBasedView<Integer> {

    public NotificationsLastNView(User user, Integer object) {
        super(user, object);
    }

}
