package org.fenixedu.bennu.notifications.master.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;

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
        return ok(view(create(payload, DispatchedNotification.class)));
    }

    @GET
    public Response getNotifications(@QueryParam("last") String lastId) {
        DispatchedNotification notification = readDomainObject(lastId);
        return ok(view(notification.getLast()));
    }
}
