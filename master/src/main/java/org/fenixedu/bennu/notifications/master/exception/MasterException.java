package org.fenixedu.bennu.notifications.master.exception;

public abstract class MasterException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MasterException(String message) {
        super(message);
    }

    public MasterException(String message, Throwable cause) {
        super(message, cause);
    }

    public MasterException(Throwable cause) {
        super(cause);
    }

}
