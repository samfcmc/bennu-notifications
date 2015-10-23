package org.fenixedu.bennu.notifications.backend.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.fenixedu.notifications.master.backend.webhook.Webhook;

import com.google.gson.JsonElement;

@Path("/webhooks")
public class WebhooksResource extends AbstractResource {

    private boolean isAuthorized(String username) {
        // FIXME: This should return true only if the user can create or update webhooks
        return true;
    }

    @POST
    public Response createWebhook(JsonElement payload, @QueryParam("token") String token) {
        String username = getUsername(token);
        if (isAuthorized(username)) {
            return ok(createAndView(payload, Webhook.class));
        } else {
            return serverError();
        }
    }

}
