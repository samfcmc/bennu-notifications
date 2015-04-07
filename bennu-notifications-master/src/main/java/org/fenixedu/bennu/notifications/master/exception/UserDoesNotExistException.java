package org.fenixedu.bennu.notifications.master.exception;

public class UserDoesNotExistException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UserDoesNotExistException() {
        super("User does not exist");
    }

}
