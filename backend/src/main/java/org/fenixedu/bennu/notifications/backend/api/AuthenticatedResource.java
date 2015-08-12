package org.fenixedu.bennu.notifications.backend.api;

import javax.ws.rs.core.Response;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.domain.exceptions.AuthorizationException;

public class AuthenticatedResource extends AbstractResource {
    @Override
    protected Response ok(Object object) {
        checkIsUserLoggedIn();
        return super.ok(object);
    }

    private void checkIsUserLoggedIn() {
        User user = getUser();
        if (user == null) {
            throw AuthorizationException.unauthorized();
        }
    }

}
