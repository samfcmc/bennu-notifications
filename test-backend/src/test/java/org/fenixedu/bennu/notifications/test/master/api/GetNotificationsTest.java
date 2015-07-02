package org.fenixedu.bennu.notifications.test.master.api;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.notifications.core.domain.Payload;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GetNotificationsTest extends AbstractAPITest {

	private static final String ID = "id";

	@Test
	public void successLastNotifications() {
		User user = generateUser();
		JsonElement payload = getNotificationPayload();
		DispatchedNotification last = new DispatchedNotification(user,
				new Payload(payload));
		new DispatchedNotification(user, new Payload(payload));
		new DispatchedNotification(user, new Payload(payload));
		JsonArray responseJson = invokeGetNotificationsAfterEndpoint(last
				.getExternalId());

		assertEquals("Result should have 2 elements", 2, responseJson.size());

		// Last should not be in result
		for (JsonElement jsonElement : responseJson) {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			String id = jsonObject.get(ID).getAsString();
			assertNotification(jsonObject);
			assertNotEquals(
					"last notification should not be included in the result",
					id, last.getExternalId());
		}
	}

	@Test
	public void successBeforeNotifications() {
		User user = generateUser();
		JsonElement payload = getNotificationPayload();
		new DispatchedNotification(user, new Payload(payload));
		new DispatchedNotification(user, new Payload(payload));
		DispatchedNotification notification3 = new DispatchedNotification(user,
				new Payload(payload));
		JsonArray responseJson = invokeGetNotificationsBeforeEndpoint(notification3
				.getExternalId());

		assertEquals("Result should have 2 elements", 2, responseJson.size());

		// Notification3 should not be in result
		for (JsonElement jsonElement : responseJson) {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			String id = jsonObject.get(ID).getAsString();
			assertNotification(jsonObject);
			assertNotEquals(
					"notification3 should not be included in the result", id,
					notification3.getExternalId());
		}
	}

	@Test
	public void noNotificationsAfterLast() {
		User user = generateUser();
		JsonElement payload = getNotificationPayload();
		new DispatchedNotification(user, new Payload(payload));
		new DispatchedNotification(user, new Payload(payload));
		DispatchedNotification last = new DispatchedNotification(user,
				new Payload(payload));
		JsonArray responseJson = invokeGetNotificationsAfterEndpoint(last
				.getExternalId());

		assertEquals("Result should be empty", 0, responseJson.size());
	}

	@Test(expected = Exception.class)
	public void lastIdDoesNotExist() {
		User user = generateUser();
		JsonElement payloadJson = getNotificationPayload();
		Payload payload = new Payload(payloadJson);
		new DispatchedNotification(user, payload);
		new DispatchedNotification(user, payload);
		new DispatchedNotification(user, payload);
		invokeGetNotificationsAfterEndpoint("fake");
	}

	@Test(expected = Exception.class)
	public void noQueryParamsProvided() {
		User user = generateUser();
		JsonElement payloadJson = getNotificationPayload();
		Payload payload = new Payload(payloadJson);
		new DispatchedNotification(user, payload);
		invokeGetLastNotificationsEndpoint();
	}

}
