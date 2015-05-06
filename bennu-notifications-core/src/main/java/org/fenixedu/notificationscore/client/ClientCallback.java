package org.fenixedu.notificationscore.client;

public interface ClientCallback {
    public void success();

    public void failed(Exception exception);

}
