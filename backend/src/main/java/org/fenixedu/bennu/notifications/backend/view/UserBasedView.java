package org.fenixedu.bennu.notifications.backend.view;

public class UserBasedView<T> {
    private String username;
    private T object;

    public UserBasedView(String username, T object) {
        this.username = username;
        this.object = object;
    }

    public String getUsername() {
        return username;
    }

    public T getObject() {
        return object;
    }
}
