package org.fenixedu.notifications.master.backend.test;

import org.fenixedu.notifications.master.backend.MasterBackend;
import org.fenixedu.notifications.master.backend.MasterBackendFactory;
import org.junit.Assert;
import org.junit.Test;

public class MasterBackendFactoryTest {

    private static final String BACKEND_CLASS = "backendClass";

    @Test
    public void success() {
        MasterBackend masterBackend = MasterBackendFactory.getBackend();
        Assert.assertNotNull("Master Backend instance must not be null", masterBackend);
        Assert.assertTrue(
                "Master Backend must be an instance of Fake Master Backend in case we do not provide a configuration file",
                masterBackend instanceof FakeMasterBackend);
    }

}
