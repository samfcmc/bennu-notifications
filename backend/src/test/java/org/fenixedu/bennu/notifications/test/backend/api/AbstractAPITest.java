package org.fenixedu.bennu.notifications.test.backend.api;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.fenixedu.bennu.core.rest.DomainObjectParamConverter;
import org.fenixedu.bennu.core.rest.JsonAwareResource;
import org.fenixedu.bennu.core.rest.JsonBodyReaderWriter;
import org.fenixedu.bennu.core.rest.JsonParamConverterProvider;
import org.fenixedu.bennu.notifications.backend.api.NotificationsResource;
import org.fenixedu.bennu.notifications.backend.json.NotificationInfoJsonAdapter;
import org.fenixedu.bennu.notifications.backend.json.NotificationViewJsonViewer;
import org.fenixedu.bennu.notifications.backend.json.NotificationsAfterByIdJsonViewer;
import org.fenixedu.bennu.notifications.backend.json.NotificationsBeforeByIdJsonViewer;
import org.fenixedu.bennu.notifications.backend.json.NotificationsLastNJsonViewer;
import org.fenixedu.bennu.notifications.backend.view.NotificationView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsAfterByIdView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsBeforeByIdView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsLastNView;
import org.fenixedu.bennu.notifications.test.backend.ff.FenixFrameworkRunner;
import org.fenixedu.notifications.master.backend.MasterBackendFactory;
import org.fenixedu.notifications.master.backend.NotificationInfo;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RunWith(FenixFrameworkRunner.class)
public abstract class AbstractAPITest extends JerseyTest {

    private static boolean done = false;
    private static final String NOTIFICATIONS_ENDPOINT = "notifications";

    protected static final String KEY_1 = "key1";
    protected static final String KEY_2 = "key2";
    protected static final String VALUE_1 = "value1";
    protected static final String VALUE_2 = "value2";
    private static final String ID = "id";
    private static final String PAYLOAD = "payload";
    private static final String READ = "read";

    @Override
    protected void configureClient(ClientConfig config) {
        super.configureClient(config);
        config.register(JsonBodyReaderWriter.class, JsonParamConverterProvider.class, DomainObjectParamConverter.class);
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(NotificationsResource.class, JsonParamConverterProvider.class,
                DomainObjectParamConverter.class, JsonBodyReaderWriter.class);
    }

    @BeforeClass
    public static void setupClass() {
        ensure();
    }

    @Before
    public void setup() {
        System.err.println("BEFORE!!");
        MasterBackendFactory.registerBackend(null);
    }

    private static void ensure() {
        if (!done) {
            registerDefaultJsonAdapters();
            done = true;
        }
    }

    private static void registerDefaultJsonAdapters() {
        JsonAwareResource.setDefault(NotificationInfo.class, NotificationInfoJsonAdapter.class);
        JsonAwareResource.setDefault(NotificationView.class, NotificationViewJsonViewer.class);
        JsonAwareResource.setDefault(NotificationsAfterByIdView.class, NotificationsAfterByIdJsonViewer.class);
        JsonAwareResource.setDefault(NotificationsBeforeByIdView.class, NotificationsBeforeByIdJsonViewer.class);
        JsonAwareResource.setDefault(NotificationsLastNView.class, NotificationsLastNJsonViewer.class);
    }

    protected JsonObject getJson(String jsonString) {
        return new Gson().fromJson(jsonString, JsonObject.class);
    }

    protected JsonArray getJsonArray(String jsonArrayString) {
        return new Gson().fromJson(jsonArrayString, JsonArray.class);
    }

    protected void assertJsonHasKey(JsonObject jsonObject, String key) {
        Assert.assertTrue("Json object should have key " + key + " " + jsonObject.toString(), jsonObject.has(key));
    }

    protected void assertJsonKeyEqualsValue(JsonObject jsonObject, String key, String expectedValue) {
        Assert.assertEquals("json key " + key + " should have value " + expectedValue, jsonObject.get(key).getAsString(),
                expectedValue);
    }

    protected JsonObject invokeCreateNotificationEndpoint(JsonObject requestJson, String token) {
        String response = invokePost(target(NOTIFICATIONS_ENDPOINT), requestJson, token);
        return getJson(response);
    }

    protected JsonArray invokeGetNotificationsAfterEndpoint(String lastId, String token) {
        WebTarget target = target(NOTIFICATIONS_ENDPOINT + "/after/" + lastId);
        String response = invokeGet(target, token);
        return getJsonArray(response);
    }

    protected JsonArray invokeGetLastNotificationsEndpoint(String token) {
        WebTarget target = target(NOTIFICATIONS_ENDPOINT);
        String response = invokeGet(target, token);
        return getJsonArray(response);
    }

    protected JsonArray invokeGetNotificationsBeforeEndpoint(String beforeId, String token) {
        WebTarget target = target(NOTIFICATIONS_ENDPOINT + "/before/" + beforeId);
        String response = invokeGet(target, token);
        return getJsonArray(response);
    }

    protected JsonObject invokeReadNotificationEndpoint(String notificationId, String token) {
        String url = NOTIFICATIONS_ENDPOINT + "/" + notificationId + "/read";
        WebTarget target = target(url);
        String response = invokePost(target, token);
        return getJson(response);
    }

    protected JsonArray invokeGetNLastNotifications(int n, String token) {
        WebTarget target = target(NOTIFICATIONS_ENDPOINT + "/last/" + n);
        String response = invokeGet(target, token);
        return getJsonArray(response);
    }

    private WebTarget getTargetWithToken(WebTarget webTarget, String token) {
        return webTarget.queryParam("token", token);
    }

    private String invokeGet(WebTarget webTarget) {
        return webTarget.request().get(String.class);
    }

    private String invokeGet(WebTarget webTarget, String token) {
        return invokeGet(getTargetWithToken(webTarget, token));
    }

    private String invokePost(WebTarget webTarget, JsonObject requestJson) {
        return webTarget.request().post(Entity.entity(requestJson.toString(), MediaType.APPLICATION_JSON), String.class);
    }

    private String invokePost(WebTarget webTarget, JsonObject requestJson, String token) {
        return invokePost(getTargetWithToken(webTarget, token), requestJson);
    }

    private String invokePost(WebTarget webTarget) {
        return invokePost(webTarget, new JsonObject());
    }

    private String invokePost(WebTarget webTarget, String token) {
        return invokePost(getTargetWithToken(webTarget, token));
    }

    protected JsonObject getNotificationPayload() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_1, VALUE_1);
        jsonObject.addProperty(KEY_2, VALUE_2);
        return jsonObject;
    }

    protected void assertNotification(JsonObject json) {
        assertJsonHasKey(json, ID);
        assertJsonHasKey(json, PAYLOAD);
        assertJsonHasKey(json, READ);
    }
}
