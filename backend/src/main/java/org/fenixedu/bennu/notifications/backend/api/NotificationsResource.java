package org.fenixedu.bennu.notifications.backend.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.backend.json.ReadNotificationJsonUpdater;
import org.fenixedu.bennu.notifications.backend.view.NotificationView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsAfterByIdView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsBeforeByIdView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsLastNView;
import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.NotificationInfo;

import com.google.gson.JsonElement;

/**
 * The Class NMasterResource. This class defines all the endpoints that the
 * NClient can invoke, such as, create notifications or get the last
 * notifications for a given user
 */

@Path("/notifications")
public class NotificationsResource extends AbstractResource {

    @POST
    public Response createNotification(JsonElement payload) {
        return ok(createAndView(payload, NotificationInfo.class));
    }

    @GET
    public Response getNotifications(@QueryParam("after") String afterId, @QueryParam("before") String beforeId,
            @QueryParam("lastN") Integer n, @QueryParam("page") int page) {
        User user = getUser();
        if (afterId != null) {
            return ok(view(new NotificationsAfterByIdView(user, afterId)));
        } else if (beforeId != null) {
            return ok(view(new NotificationsBeforeByIdView(user, beforeId)));
        } else if (n != null) {
            return ok(view(new NotificationsLastNView(user, n)));
        } else {
            return serverError();
        }
    }

    @Path("/{notification}/read")
    @POST
    public Response read(@PathParam("notification") String id) {
        User user = getUser();
        NotificationInfo notification = Master.getInstance().getNotification(user.getUsername(), id);
        return ok(updateAndView(new NotificationView(user, notification), ReadNotificationJsonUpdater.class));
    }
}
