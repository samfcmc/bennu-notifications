package org.fenixedu.bennu.notifications.backend.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.backend.json.GetUnreadJsonViewer;
import org.fenixedu.bennu.notifications.backend.json.ReadNotificationJsonUpdater;
import org.fenixedu.bennu.notifications.backend.view.GetUnreadView;
import org.fenixedu.bennu.notifications.backend.view.NotificationView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsAfterByIdView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsBeforeByIdView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsLastNView;
import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.NotificationInfo;

import com.google.gson.JsonElement;

/**
 * The Class NMasterResource. This class defines all the endpoints that the
 * Client can invoke, such as, create notifications or get the last
 * notifications for a given user
 */

@Path("/notifications")
public class NotificationsResource extends AuthenticatedResource {

    @POST
    public Response createNotification(JsonElement payload) {
        return ok(createAndView(payload, NotificationInfo.class));
    }

    @Path("/after/{id}")
    @GET
    public Response getAfter(@PathParam("id") String id) {
        User user = getUser();
        return ok(view(new NotificationsAfterByIdView(user, id)));
    }

    @Path("/before/{id}")
    @GET
    public Response getBefore(@PathParam("id") String id) {
        User user = getUser();
        return ok(view(new NotificationsBeforeByIdView(user, id)));
    }

    @Path("/last/{n}")
    @GET
    public Response getLastN(@PathParam("n") Integer n) {
        User user = getUser();
        return ok(view(new NotificationsLastNView(user, n)));
    }

    @Path("/{id}/read")
    @POST
    public Response read(@PathParam("id") String id) {
        User user = getUser();
        NotificationInfo notification = Master.getInstance().getNotification(user.getUsername(), id);
        return ok(updateAndView(new NotificationView(user, notification), ReadNotificationJsonUpdater.class));
    }

    @Path("/unread")
    @GET
    public Response getUnread() {
        User user = getUser();
        return ok(view(new GetUnreadView(user), GetUnreadJsonViewer.class));
    }
}
