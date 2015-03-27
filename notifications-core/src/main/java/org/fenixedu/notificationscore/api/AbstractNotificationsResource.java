package org.fenixedu.notificationscore.api;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fenixedu.bennu.core.rest.JsonAwareResource;

@Produces(MediaType.APPLICATION_JSON)
public class AbstractNotificationsResource extends JsonAwareResource {

    protected Response ok(Object object) {
        return Response.ok(object).build();
    }

}
