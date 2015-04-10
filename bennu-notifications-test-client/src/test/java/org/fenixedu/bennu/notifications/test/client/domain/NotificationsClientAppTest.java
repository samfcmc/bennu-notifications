package org.fenixedu.bennu.notifications.test.client.domain;

import static org.junit.Assert.assertNotNull;

import org.fenixedu.bennu.notifications.client.domain.NotificationsClientApp;
import org.fenixedu.bennu.notifications.test.AbstractTest;
import org.junit.Test;

public class NotificationsClientAppTest extends AbstractTest {

    @Test
    public void testSingleton() {
        NotificationsClientApp instance = NotificationsClientApp.getInstance();
        assertNotNull("Instance should not be null", instance);
        //assertNotNull("Bennu instance of app instance should not be null", );
        // assertEquals("Bennu instances should be the same", instance.getRoot(), Bennu.getInstance());
    }

}
