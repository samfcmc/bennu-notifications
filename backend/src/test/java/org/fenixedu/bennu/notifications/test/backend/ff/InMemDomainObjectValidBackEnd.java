package org.fenixedu.bennu.notifications.test.backend.ff;

import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.backend.jvstm.JVSTMBackEnd;
import pt.ist.fenixframework.backend.jvstm.repository.NoRepository;

public class InMemDomainObjectValidBackEnd extends JVSTMBackEnd {

    InMemDomainObjectValidBackEnd() {
        super(new NoRepository());
    }

    @Override
    public boolean isDomainObjectValid(DomainObject object) {
        return object != null;
    }

}
