package org.fenixedu.bennu.notifications.backend.view;

import org.fenixedu.bennu.core.domain.User;

public class DomainObjectIdView extends UserBasedView<String> {

    public DomainObjectIdView(User user, String object) {
        super(user, object);
    }

}
