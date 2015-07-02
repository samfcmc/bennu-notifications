package org.fenixedu.bennu.notifications.test.backend.api;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.logout;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.fenixedu.bennu.core.groups.AnonymousGroup;
import org.fenixedu.bennu.core.groups.AnyoneGroup;
import org.fenixedu.bennu.core.groups.CustomGroupRegistry;
import org.fenixedu.bennu.core.groups.CustomGroupRegistry.BooleanParser;
import org.fenixedu.bennu.core.groups.CustomGroupRegistry.DateTimeParser;
import org.fenixedu.bennu.core.groups.CustomGroupRegistry.StringParser;
import org.fenixedu.bennu.core.groups.LoggedGroup;
import org.fenixedu.bennu.core.groups.NobodyGroup;
import org.fenixedu.bennu.core.groups.UserGroup;
import org.fenixedu.bennu.core.rest.DomainObjectParamConverter;
import org.fenixedu.bennu.core.rest.JsonAwareResource;
import org.fenixedu.bennu.core.rest.JsonBodyReaderWriter;
import org.fenixedu.bennu.core.rest.JsonParamConverterProvider;
import org.fenixedu.bennu.notifications.backend.api.NotificationsResource;
import org.fenixedu.bennu.notifications.backend.json.DispatchedNotificationJsonAdapter;
import org.fenixedu.bennu.notifications.backend.json.NotificationViewJsonViewer;
import org.fenixedu.bennu.notifications.backend.json.NotificationsAfterByIdJsonViewer;
import org.fenixedu.bennu.notifications.backend.json.NotificationsBeforeByIdJsonViewer;
import org.fenixedu.bennu.notifications.backend.json.NotificationsLastNJsonViewer;
import org.fenixedu.bennu.notifications.backend.json.PayloadJsonViewer;
import org.fenixedu.bennu.notifications.backend.view.NotificationView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsAfterByIdView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsBeforeByIdView;
import org.fenixedu.bennu.notifications.backend.view.NotificationsLastNView;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.bennu.notifications.test.ff.FenixFrameworkRunner;
import org.fenixedu.notifications.core.domain.Payload;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;

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
    @Atomic(mode = TxMode.WRITE)
    public static void initObjects() {
        ensure();
    }

    @After
    @Atomic(mode = TxMode.WRITE)
    public void after() {
        logout();
    }

    private static void ensure() {
        if (!done) {
            CustomGroupRegistry.registerCustomGroup(AnonymousGroup.class);
            CustomGroupRegistry.registerCustomGroup(AnyoneGroup.class);
            CustomGroupRegistry.registerCustomGroup(LoggedGroup.class);
            CustomGroupRegistry.registerCustomGroup(NobodyGroup.class);
            CustomGroupRegistry.registerCustomGroup(UserGroup.class);
            CustomGroupRegistry.registerArgumentParser(UserGroup.UserArgumentParser.class);
            CustomGroupRegistry.registerArgumentParser(BooleanParser.class);
            CustomGroupRegistry.registerArgumentParser(StringParser.class);
            CustomGroupRegistry.registerArgumentParser(DateTimeParser.class);

            registerDefaultJsonAdapters();

            done = true;
        }
    }

    private static void registerDefaultJsonAdapters() {
        JsonAwareResource.setDefault(DispatchedNotification.class, DispatchedNotificationJsonAdapter.class);
        JsonAwareResource.setDefault(Payload.class, PayloadJsonViewer.class);
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

    protected JsonObject invokeCreateNotificationEndpoint(JsonObject requestJson) {
        String response = invokePost(target(NOTIFICATIONS_ENDPOINT), requestJson);
        return getJson(response);
    }

    protected JsonArray invokeGetNotificationsAfterEndpoint(String lastId) {
        WebTarget target = target(NOTIFICATIONS_ENDPOINT).queryParam("after", lastId);
        String response = invokeGet(target);
        return getJsonArray(response);
    }

    protected JsonArray invokeGetLastNotificationsEndpoint() {
        WebTarget target = target(NOTIFICATIONS_ENDPOINT);
        String response = invokeGet(target);
        return getJsonArray(response);
    }

    protected JsonArray invokeGetNotificationsBeforeEndpoint(String beforeId) {
        WebTarget target = target(NOTIFICATIONS_ENDPOINT).queryParam("before", beforeId);
        String response = invokeGet(target);
        return getJsonArray(response);
    }

    protected JsonObject invokeReadNotificationEndpoint(String notificationId) {
        String url = NOTIFICATIONS_ENDPOINT + "/" + notificationId + "/read";
        WebTarget target = target(url);
        String response = invokePost(target);
        return getJson(response);
    }

    protected JsonArray invokeGetNLastNotifications(int n) {
        WebTarget target = target(NOTIFICATIONS_ENDPOINT).queryParam("lastN", n);
        String response = invokeGet(target);
        return getJsonArray(response);
    }

    private String invokeGet(WebTarget webTarget) {
        return webTarget.request().get(String.class);
    }

    private String invokePost(WebTarget webTarget, JsonObject requestJson) {
        return webTarget.request().post(Entity.entity(requestJson.toString(), MediaType.APPLICATION_JSON), String.class);
    }

    private String invokePost(WebTarget webTarget) {
        return invokePost(webTarget, new JsonObject());
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
