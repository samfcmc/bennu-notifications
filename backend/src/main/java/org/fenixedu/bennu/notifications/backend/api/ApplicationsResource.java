package org.fenixedu.bennu.notifications.backend.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.JsonElement;

@Path("/applications")
public class ApplicationsResource extends AbstractResource {

    @POST
    public Response registerApplication(JsonElement payload) {
        //TODO: Register an application
        return ok(null);
    }

    @GET
    public Response getApplications() {
        //TODO: Get all applications using this notifications service
        return ok(null);
    }

}
