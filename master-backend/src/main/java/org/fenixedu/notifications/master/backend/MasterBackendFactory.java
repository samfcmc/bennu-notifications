package org.fenixedu.notifications.master.backend;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.fenixedu.notifications.master.backend.exception.CannotAccessMasterBackendClassConstructorException;
import org.fenixedu.notifications.master.backend.exception.MissingClassNamePropertiesFileException;
import org.fenixedu.notifications.master.backend.exception.WrongMasterBackendClassNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MasterBackendFactory {
    private static final String FILE_NAME = "master-backend.properties";
    private static final String BACKEND_CLASS = "backendClass";
    private static Logger logger = LoggerFactory.getLogger(MasterBackend.class);

    private MasterBackendFactory() {
    }

    public static MasterBackend getBackend() {
        Properties properties = readPropertiesFile(FILE_NAME);
        if (properties == null) {
            logger.info("No master backend properties file found. Using in memory master backend");
            return new InMemMasterBackend();
        } else {
            return getBackend(properties);
        }
    }

    public static MasterBackend getBackend(Properties properties) {
        if (properties.containsKey(BACKEND_CLASS)) {
            String className = properties.getProperty(BACKEND_CLASS);
            MasterBackend backend = getBackendInstance(className);
            return backend;
        } else {
            throw new MissingClassNamePropertiesFileException();
        }
    }

    private static Properties readPropertiesFile(String fileName) {
        Properties properties = null;
        InputStream inputStream = MasterBackendFactory.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) {
            try {
                properties = new Properties();
                properties.load(inputStream);
            } catch (IOException e) {
                return null;
            }
        }
        return properties;
    }

    private static MasterBackend getBackendInstance(String className) {
        try {
            Class<? extends MasterBackend> clazz = Class.forName(className).asSubclass(MasterBackend.class);
            MasterBackend backend = clazz.newInstance();
            return backend;
        } catch (ClassNotFoundException e) {
            throw new WrongMasterBackendClassNameException(className);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CannotAccessMasterBackendClassConstructorException(className);
        }
    }

}
