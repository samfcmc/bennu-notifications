package org.fenixedu.notifications.master.backend.test;

import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.fenixedu.notifications.master.backend.InMemMasterBackend;
import org.fenixedu.notifications.master.backend.MasterBackend;
import org.fenixedu.notifications.master.backend.MasterBackendFactory;
import org.fenixedu.notifications.master.backend.NotificationInfo;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;

public class InMemBackendTest {

    @Before
    public void setup() {
        MasterBackendFactory.registerBackend(new InMemMasterBackend());
    }

    @Test
    public void getUnread() {
        MasterBackend backend = MasterBackendFactory.getBackend();
        String username = "user";
        JsonObject payload = new JsonObject();
        backend.createNotification(username, payload);
        backend.createNotification(username, payload);
        backend.createNotification(username, payload);
        backend.createNotification(username, payload);

        Collection<NotificationInfo> notifications = backend.getUnread(username);
        for (NotificationInfo notification : notifications) {
            assertFalse("Created notification should not be read", notification.isRead());
        }
    }

}
