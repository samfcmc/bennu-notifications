package org.fenixedu.notifications.master.backend.exception;

public class MasterBackendConfigurationFileNotFoundException extends MasterBackendException {

    public MasterBackendConfigurationFileNotFoundException(String fileName) {
        super("Master backend configuration file " + fileName + " not found");
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
