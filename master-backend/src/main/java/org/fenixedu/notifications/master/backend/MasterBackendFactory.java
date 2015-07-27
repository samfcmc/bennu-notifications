package org.fenixedu.notifications.master.backend;

import java.io.IOException;

import org.fenixedu.notifications.master.backend.annotation.MasterBackendImplementationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.infomas.annotation.AnnotationDetector;

public class MasterBackendFactory {
    private static final String FILE_NAME = "master-backend.properties";
    private static final String BACKEND_CLASS = "backendClass";
    private static Logger logger = LoggerFactory.getLogger(MasterBackend.class);
    private static MasterBackend backend;

    private MasterBackendFactory() {
    }

    public static MasterBackend getBackend() {
        if (backend == null) {
            initBackend();
        }
        return backend;
    }

    private static void initBackend() {
        AnnotationDetector detector = new AnnotationDetector(new MasterBackendImplementationReporter());
        try {
            detector.detect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (backend == null) {
            // No class annotated was found
            backend = new InMemMasterBackend();
        }
    }

    public static void registerBackend(MasterBackend newBackend) {
        backend = newBackend;
    }

}
