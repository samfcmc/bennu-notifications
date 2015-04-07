package org.fenixedu.notificationscore.api;

import javax.management.Notification;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

/**
 * The Class NMasterResource.
 * This class defines all the endpoints that the NClient can invoke, such as,
 * create notifications or get the last notifications for a given user
 */

@Path("/bennu-notifications")
public class NMasterResource extends AbstractNotificationsResource {

    @POST
    public Response createNotification(JsonObject payload) {
        return ok(view(create(payload, Notification.class)));
    }
}
