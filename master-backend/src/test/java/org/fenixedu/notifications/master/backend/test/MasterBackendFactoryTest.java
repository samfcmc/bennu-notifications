package org.fenixedu.notifications.master.backend.test;

import org.fenixedu.notifications.master.backend.MasterBackend;
import org.fenixedu.notifications.master.backend.MasterBackendFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MasterBackendFactoryTest {

    @Before
    public void setup() {
        MasterBackendFactory.registerBackend(null);
    }

    @Test
    public void success() {
        MasterBackend masterBackend = MasterBackendFactory.getBackend();
        Assert.assertNotNull("Master Backend instance must not be null", masterBackend);
        Assert.assertTrue(
                "Master Backend must be an instance of Fake Master Backend in case we do not provide a class annotated with @MasterBackendImplementation",
                masterBackend instanceof FakeMasterBackend);
    }

}
