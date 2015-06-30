package org.fenixedu.bennu.notifications.backend.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/dummy")
public class DummyResource extends AbstractNotificationsResource {

    public static class DummyView {
        private String value;

        public DummyView(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @GET
    public Response getDummy() {
        return ok(view(new DummyView("test")));
    }

}
