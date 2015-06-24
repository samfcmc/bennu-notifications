package org.fenixedu.bennu.notifications.client;

public interface ClientCallback {
    public void success();

    public void failed(Exception exception);

}
