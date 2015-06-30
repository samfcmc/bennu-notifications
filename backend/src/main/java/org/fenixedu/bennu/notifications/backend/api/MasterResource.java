package org.fenixedu.bennu.notifications.backend.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;

import com.google.gson.JsonObject;

/**
 * The Class NMasterResource. This class defines all the endpoints that the
 * NClient can invoke, such as, create notifications or get the last
 * notifications for a given user
 */

@Path("/notifications")
public class MasterResource extends AbstractNotificationsResource {

	@POST
	public Response createNotification(JsonObject payload) {
		return ok(view(create(payload, DispatchedNotification.class)));
	}

	@GET
	public Response getNotifications(@QueryParam("last") String lastId,
			@QueryParam("before") String beforeId, @QueryParam("page") int page) {
		if (lastId != null) {
			DispatchedNotification notification = readDomainObject(lastId);
			return ok(view(notification.getLast()));
		} else if (beforeId != null) {
			DispatchedNotification notification = readDomainObject(beforeId);
			return ok(view(notification.getBefore()));
		} else {
			return serverError();
		}
	}
}
