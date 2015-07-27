package org.fenixedu.notifications.master.backend.annotation;

import java.lang.annotation.Annotation;

import org.fenixedu.notifications.master.backend.MasterBackend;
import org.fenixedu.notifications.master.backend.MasterBackendFactory;
import org.fenixedu.notifications.master.backend.exception.CannotAccessMasterBackendClassConstructorException;
import org.fenixedu.notifications.master.backend.exception.WrongMasterBackendClassNameException;

import eu.infomas.annotation.AnnotationDetector.TypeReporter;

public class MasterBackendImplementationReporter implements TypeReporter {

    @SuppressWarnings("rawtypes")
    private static Class[] supportedAnnotations = { MasterBackendImplementation.class };

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Annotation>[] annotations() {
        return supportedAnnotations;
    }

    @Override
    public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
        if (annotation == MasterBackendImplementation.class) {
            try {
                Class<? extends MasterBackend> clazz = Class.forName(className).asSubclass(MasterBackend.class);
                MasterBackend masterBackend = clazz.newInstance();
                MasterBackendFactory.registerBackend(masterBackend);
            } catch (ClassNotFoundException e) {
                throw new WrongMasterBackendClassNameException(className);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new CannotAccessMasterBackendClassConstructorException(className);
            }
        }

    }

}
