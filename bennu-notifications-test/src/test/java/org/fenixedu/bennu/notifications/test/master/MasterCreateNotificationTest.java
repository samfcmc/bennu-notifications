package org.fenixedu.bennu.notifications.test.master;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.findUser;
import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.fenixedu.bennu.notifications.test.utils.TestUtils.getInexistantUsername;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.NMaster;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.bennu.notifications.master.exception.UserDoesNotExistException;
import org.fenixedu.bennu.notifications.test.AbstractTest;
import org.junit.Test;

import com.google.gson.JsonObject;

public class MasterCreateNotificationTest extends AbstractTest {

    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";
    private static final String VALUE_1 = "value1";
    private static final String VALUE_2 = "value2";

    @Test
    public void successCreateOne() {
        User user = generateUser();
        JsonObject payload = getPayload();
        DispatchedNotification notification = NMaster.createNotification(user, payload);

        assertNotNull("Created notification should not be null", notification);
        assertEquals("The last notification should be the created one", notification, user.getLastNotification());
        assertNull("Previous notification should be null", user.getLastNotification().getPrevious());
    }

    @Test
    public void successCreateMultiple() {
        User user = generateUser();
        JsonObject payload = getPayload();
        DispatchedNotification notificationT0 = NMaster.createNotification(user, payload);
        DispatchedNotification notificationT1 = NMaster.createNotification(user, payload);
        DispatchedNotification notificationT2 = NMaster.createNotification(user, payload);
        DispatchedNotification notificationT3 = NMaster.createNotification(user, payload);
        DispatchedNotification lastNotification = user.getLastNotification();
        DispatchedNotification previous = lastNotification.getPrevious();

        assertEquals("Last notification should be T3", notificationT3, lastNotification);
        assertEquals("Previous of T3 should be T2", notificationT2, previous);
        previous = previous.getPrevious();
        assertEquals("Previous of T2 should be T1", notificationT1, previous);
        previous = previous.getPrevious();
        assertEquals("Previous of T1 should be T0", notificationT0, previous);
        previous = previous.getPrevious();
        assertNull("T0 should not have any previous", previous);
    }

    @Test(expected = UserDoesNotExistException.class)
    public void userDoesNotExist() {
        String username = getInexistantUsername();
        User user = findUser(username);
        JsonObject payload = getPayload();
        NMaster.createNotification(user, payload);
    }

    protected JsonObject getPayload() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_1, VALUE_1);
        jsonObject.addProperty(KEY_2, VALUE_2);

        return jsonObject;
    }

}
