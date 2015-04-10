package org.fenixedu.bennu.notifications.master.api;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fenixedu.bennu.core.rest.BennuRestResource;

import com.google.gson.JsonElement;

@Produces(MediaType.APPLICATION_JSON)
public class AbstractNotificationsResource extends BennuRestResource {

    protected Response ok(Object object) {
        return Response.ok(object).build();
    }

    protected Object create(JsonElement payload, Class<?> clazz) {
        return create(payload.toString(), clazz);
    }

}
