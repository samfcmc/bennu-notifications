package org.fenixedu.notifications.master.backend.exception;

public class WrongMasterBackendClassNameException extends MasterBackendException {

    public WrongMasterBackendClassNameException(String className) {
        super("Cannot find backend class " + className);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
