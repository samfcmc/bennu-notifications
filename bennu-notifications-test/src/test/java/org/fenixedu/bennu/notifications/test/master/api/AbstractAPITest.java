package org.fenixedu.bennu.notifications.test.master.api;

import javax.ws.rs.client.Entity;
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
import org.fenixedu.bennu.notifications.master.api.NMasterResource;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.bennu.notifications.master.json.DispatchedNotificationJsonAdapter;
import org.fenixedu.bennu.notifications.test.ff.FenixFrameworkRunner;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RunWith(FenixFrameworkRunner.class)
public abstract class AbstractAPITest extends JerseyTest {

    private static boolean done = false;

    @Override
    protected void configureClient(ClientConfig config) {
        super.configureClient(config);
        config.register(JsonBodyReaderWriter.class, JsonParamConverterProvider.class, DomainObjectParamConverter.class);
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(NMasterResource.class, JsonParamConverterProvider.class, DomainObjectParamConverter.class,
                JsonBodyReaderWriter.class);
    }

    @BeforeClass
    @Atomic(mode = TxMode.WRITE)
    public static void initObjects() {
        ensure();
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
    }

    protected JsonObject getJson(String jsonString) {
        return new Gson().fromJson(jsonString, JsonObject.class);
    }

    protected void assertJsonHasKey(JsonObject jsonObject, String key) {
        Assert.assertTrue("Json object should have key " + key, jsonObject.has(key));
    }

    protected void assertJsonKeyEqualsValue(JsonObject jsonObject, String key, String expectedValue) {
        Assert.assertEquals("json key " + key + " should have value " + expectedValue, jsonObject.get(key).getAsString(),
                expectedValue);
    }

    protected JsonObject invokeCreateNotificationEndpoint(JsonObject requestJson) {
        String response =
                target("bennu-notifications").request().post(Entity.entity(requestJson.toString(), MediaType.APPLICATION_JSON),
                        String.class);
        return getJson(response);
    }

}
