package org.fenixedu.notifications.master.backend.test;

import java.util.Properties;

import org.fenixedu.notifications.master.backend.InMemMasterBackend;
import org.fenixedu.notifications.master.backend.MasterBackend;
import org.fenixedu.notifications.master.backend.MasterBackendFactory;
import org.fenixedu.notifications.master.backend.exception.CannotAccessMasterBackendClassConstructorException;
import org.fenixedu.notifications.master.backend.exception.MissingClassNamePropertiesFileException;
import org.fenixedu.notifications.master.backend.exception.WrongMasterBackendClassNameException;
import org.junit.Assert;
import org.junit.Test;

public class MasterBackendFactoryTest {

    private static final String BACKEND_CLASS = "backendClass";

    @Test
    public void successNoFile() {
        MasterBackend masterBackend = MasterBackendFactory.getBackend();
        Assert.assertNotNull("Master Backend instance must not be null", masterBackend);
        Assert.assertTrue(
                "Master Backend must be an instance of In Memory Backend in case we do not provide a configuration file",
                masterBackend instanceof InMemMasterBackend);
    }

    @Test
    public void successValidFile() {
        Properties properties = new Properties();
        properties.put(BACKEND_CLASS, InMemMasterBackend.class.getName());
        MasterBackend masterBackend = MasterBackendFactory.getBackend(properties);
        Assert.assertNotNull("Master Backend instance must not be null", masterBackend);
        Assert.assertTrue(
                "Master Backend must be an instance of In Memory Backend in case we do not provide a configuration file",
                masterBackend instanceof InMemMasterBackend);
    }

    @Test(expected = MissingClassNamePropertiesFileException.class)
    public void validFileNoClassNameKey() {
        Properties properties = new Properties();
        MasterBackendFactory.getBackend(properties);
    }

    @Test(expected = WrongMasterBackendClassNameException.class)
    public void validFileClassNameDoesNotExist() {
        Properties properties = new Properties();
        properties.put(BACKEND_CLASS, "Fake class");
        MasterBackendFactory.getBackend(properties);
    }

    @Test(expected = CannotAccessMasterBackendClassConstructorException.class)
    public void validFileNotEmptyConstructor() {
        Properties properties = new Properties();
        properties.put(BACKEND_CLASS, FakeMasterBackend.class.getName());
        MasterBackendFactory.getBackend(properties);
    }

}
