package org.fenixedu.bennu.notifications.client.payload;

import java.util.HashMap;
import java.util.Map;

import org.fenixedu.bennu.notifications.client.payload.exception.NoDescriptionProvidedException;
import org.fenixedu.bennu.notifications.client.payload.exception.NoTypeProvidedException;

public class NotificationPayload {

    private String link;
    private String type;
    private Map<String, String> descriptions;
    private String image;

    private NotificationPayload(Builder builder) {
        // Validate mandatory arguments
        if (builder.descriptions.isEmpty()) {
            throw new NoDescriptionProvidedException();
        }
        if (builder.type == null) {
            throw new NoTypeProvidedException();
        }

        this.link = builder.link;
        this.type = builder.type;
        this.descriptions = builder.descriptions;
        this.image = builder.image;
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public Map<String, String> getDescriptions() {
        return descriptions;
    }

    public String getDescription(String language) {
        return descriptions.get(language);
    }

    /*
     * Builder
     */
    public static class Builder {

        private Map<String, String> descriptions;
        private String type;

        //Optional
        private String link;
        private String image;

        public Builder() {
            this.descriptions = new HashMap<>();
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder description(String language, String description) {
            this.descriptions.put(language, description);
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public NotificationPayload build() {
            return new NotificationPayload(this);
        }
    }

}
