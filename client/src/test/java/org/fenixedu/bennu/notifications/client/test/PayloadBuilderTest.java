package org.fenixedu.bennu.notifications.client.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.fenixedu.bennu.notifications.client.payload.NotificationPayload;
import org.fenixedu.bennu.notifications.client.payload.exception.NoDescriptionProvidedException;
import org.fenixedu.bennu.notifications.client.payload.exception.NoTypeProvidedException;
import org.junit.Test;

public class PayloadBuilderTest {
    private static final String LANGUAGE_EN = "en";
    private static final String DESCRIPTION = "description";
    private static final String TYPE = "NOTIFICATION_TYPE";
    private static final String IMAGE = "image";
    private static final String LINK = "link";

    @Test
    public void success() {
        NotificationPayload.Builder builder = new NotificationPayload.Builder();
        builder.description(LANGUAGE_EN, DESCRIPTION);
        builder.type(TYPE);
        builder.image(IMAGE);
        builder.link(LINK);
        NotificationPayload payload = builder.build();

        assertEquals("payload does not have the expected description for language EN", payload.getDescription(LANGUAGE_EN),
                DESCRIPTION);
        assertEquals("payload does not have the expected type", payload.getType(), TYPE);
        assertEquals("payload does not have the expected image", payload.getImage(), IMAGE);
        assertEquals("payload does not have the expected link", payload.getLink(), LINK);
    }

    @Test
    public void withoutImage() {
        NotificationPayload.Builder builder = new NotificationPayload.Builder();
        builder.description(LANGUAGE_EN, DESCRIPTION);
        builder.type(TYPE);
        builder.link(LINK);
        NotificationPayload payload = builder.build();

        assertEquals("payload does not have the expected description for language EN", payload.getDescription(LANGUAGE_EN),
                DESCRIPTION);
        assertEquals("payload does not have the expected type", payload.getType(), TYPE);
        assertNull("payload have image", payload.getImage());
        assertEquals("payload does not have the expected link", payload.getLink(), LINK);
    }

    @Test
    public void withoutLink() {
        NotificationPayload.Builder builder = new NotificationPayload.Builder();
        builder.description(LANGUAGE_EN, DESCRIPTION);
        builder.type(TYPE);
        builder.image(IMAGE);
        NotificationPayload payload = builder.build();

        assertEquals("payload does not have the expected description for language EN", payload.getDescription(LANGUAGE_EN),
                DESCRIPTION);
        assertEquals("payload does not have the expected type", payload.getType(), TYPE);
        assertNull("payload have link", payload.getLink());
        assertEquals("payload does not have the expected image", payload.getImage(), IMAGE);
    }

    @Test(expected = NoTypeProvidedException.class)
    public void withoutType() {
        NotificationPayload.Builder builder = new NotificationPayload.Builder();
        builder.description(LANGUAGE_EN, DESCRIPTION);
        builder.link(LINK);
        builder.build();
    }

    @Test(expected = NoDescriptionProvidedException.class)
    public void withoutDescription() {
        NotificationPayload.Builder builder = new NotificationPayload.Builder();
        builder.type(TYPE);
        builder.image(IMAGE);
        builder.link(LINK);
        builder.build();
    }

}
