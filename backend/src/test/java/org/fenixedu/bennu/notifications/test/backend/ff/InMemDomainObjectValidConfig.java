package org.fenixedu.bennu.notifications.test.backend.ff;

import pt.ist.fenixframework.backend.BackEndId;
import pt.ist.fenixframework.backend.jvstm.JVSTMConfig;

public class InMemDomainObjectValidConfig extends JVSTMConfig {

    public InMemDomainObjectValidConfig() {
        this.appNameFromString(BackEndId.getBackEndId().getAppName());
        this.backEnd = new InMemDomainObjectValidBackEnd();
    }
}
