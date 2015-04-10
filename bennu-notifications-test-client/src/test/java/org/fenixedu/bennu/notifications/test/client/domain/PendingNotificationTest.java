package org.fenixedu.bennu.notifications.test.client.domain;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.client.domain.PendingNotification;
import org.fenixedu.bennu.notifications.test.AbstractTest;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PendingNotificationTest extends AbstractTest {
    
    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";
    private static final String VALUE_1 = "value1";
    private static final String VALUE_2 = "value2";
    
    @Test
    public void success() {
        User user = generateUser();
        JsonElement payload = getPayload();
        PendingNotification notification = new PendingNotification(user, payload);
        
        assertNotNull("New pending notification should not be null", notification);
        assertTrue("User should have one pending notification", notification.getUserSet().contains(user));
    }
    
    private JsonElement getPayload() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_1, VALUE_1);
        jsonObject.addProperty(KEY_2, VALUE_2);
        return jsonObject;
    }

}
