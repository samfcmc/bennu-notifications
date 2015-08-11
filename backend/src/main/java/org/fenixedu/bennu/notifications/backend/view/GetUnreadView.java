package org.fenixedu.bennu.notifications.backend.view;

import org.fenixedu.bennu.core.domain.User;

public class GetUnreadView {
    private User user;

    public GetUnreadView(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
