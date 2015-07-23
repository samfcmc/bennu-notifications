package org.fenixedu.notifications.master.backend.exception;

public class CannotAccessMasterBackendClassConstructorException extends MasterBackendException {

    public CannotAccessMasterBackendClassConstructorException(String className) {
        super("Cannot access empty constructor of " + className);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
