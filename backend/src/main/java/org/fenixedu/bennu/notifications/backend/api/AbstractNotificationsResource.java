package org.fenixedu.bennu.notifications.backend.api;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fenixedu.bennu.core.json.JsonCreator;
import org.fenixedu.bennu.core.json.JsonUpdater;
import org.fenixedu.bennu.core.rest.BennuRestResource;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Produces(MediaType.APPLICATION_JSON)
public class AbstractNotificationsResource extends BennuRestResource {

    protected Response ok(Object object) {
        return Response.ok(object).build();
    }

    protected <T> T create(JsonElement payload, Class<T> classToCreate) {
        return create(payload.toString(), classToCreate);
    }

    protected <T> T create(JsonElement payload, Class<T> classToCreate, Class<? extends JsonCreator<T>> creatorClass) {
        return create(payload.toString(), classToCreate, creatorClass);
    }

    protected <T> Object createAndView(JsonElement payload, Class<T> classToCreate) {
        Object created = create(payload, classToCreate);
        return view(created);
    }

    protected <T> Object createAndView(JsonElement payload, Class<T> classToCreate, Class<? extends JsonCreator<T>> creatorClass) {
        T created = create(payload, classToCreate, creatorClass);
        return view(created);
    }

    protected Response serverError() {
        return Response.serverError().build();
    }

    protected <T> T update(JsonElement payload, T object, Class<? extends JsonUpdater<T>> updaterClass) {
        return update(payload.toString(), object, updaterClass);
    }

    protected <T> T update(T object, Class<? extends JsonUpdater<T>> updaterClass) {
        return update(new JsonObject(), object, updaterClass);
    }

    protected <T> Object updateAndView(JsonElement payload, T object, Class<? extends JsonUpdater<T>> updaterClass) {
        T updated = update(payload, object, updaterClass);
        return view(updated);
    }

    protected <T> Object updateAndView(T object, Class<? extends JsonUpdater<T>> updaterClass) {
        return updateAndView(new JsonObject(), object, updaterClass);
    }

}
