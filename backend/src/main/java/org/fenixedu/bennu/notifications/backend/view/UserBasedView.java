package org.fenixedu.bennu.notifications.backend.view;

import org.fenixedu.bennu.core.domain.User;

public class UserBasedView<T> {
    private User user;
    private T object;

    public UserBasedView(User user, T object) {
        this.user = user;
        this.object = object;
    }

    public User getUser() {
        return user;
    }

    public T getObject() {
        return object;
    }
}
