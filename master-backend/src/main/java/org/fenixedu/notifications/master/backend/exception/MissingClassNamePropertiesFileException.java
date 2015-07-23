package org.fenixedu.notifications.master.backend.exception;

public class MissingClassNamePropertiesFileException extends MasterBackendException {

    public MissingClassNamePropertiesFileException() {
        super("Missing property className in master backend configuration file");
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
