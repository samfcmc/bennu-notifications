package org.fenixedu.bennu.notifications.test.ff;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.backend.BackEndId;
import pt.ist.fenixframework.backend.jvstm.JVSTMConfig;

/***
 * 
 * Used to initialize {@link FenixFramework} with {@link InMemBackEnd}
 * 
 * Used in fenix-framework.properties for testing.
 * 
 *
 */
public class InMemConfig extends JVSTMConfig {

    public InMemConfig() {
        this.appNameFromString(BackEndId.getBackEndId().getAppName());
        this.backEnd = new InMemBackEnd();
    }
}