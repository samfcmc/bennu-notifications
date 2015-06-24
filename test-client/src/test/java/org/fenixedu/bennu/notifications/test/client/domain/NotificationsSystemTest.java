package org.fenixedu.bennu.notifications.test.client.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.bennu.notifications.client.domain.NotificationsSystem;
import org.fenixedu.bennu.notifications.test.AbstractTest;
import org.junit.Test;

public class NotificationsSystemTest extends AbstractTest {

    @Test
    public void testSingleton() {
        NotificationsSystem instance = NotificationsSystem.getInstance();
        assertNotNull("Instance should not be null", instance);
        assertNotNull("Bennu instance of app instance should not be null", instance.getBennu());
        assertEquals("Bennu instances should be the same", instance.getBennu(), Bennu.getInstance());
    }

}
