package org.fenixedu.bennu.notifications.backend.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.webhook.Webhook;

import com.google.gson.JsonElement;

@Path("/webhooks")
public class WebhooksResource extends AbstractResource {

    private boolean isAuthorized(User user) {
        // FIXME: This should return true only if the user can create or update webhooks
        return true;
    }

    @POST
    public Response createWebhook(JsonElement payload) {
        User user = getUser();
        if (isAuthorized(user)) {
            return ok(createAndView(payload, Webhook.class));
        } else {
            return serverError();
        }
    }

    @GET
    public Response getWebhooks() {
        if (isUserLoggedIn()) {
            return ok(view(Master.getInstance().getWebhooks()));
        } else {
            return serverError();
        }

    }

}
