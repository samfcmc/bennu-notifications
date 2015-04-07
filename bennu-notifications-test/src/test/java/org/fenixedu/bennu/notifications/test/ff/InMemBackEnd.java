package org.fenixedu.bennu.notifications.test.ff;

import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.backend.jvstm.JVSTMBackEnd;
import pt.ist.fenixframework.backend.jvstm.repository.NoRepository;

/***
 * 
 * This backend is necessary since {@link JVSTMBackEnd} throws {@link UnsupportedOperationException} when invoking
 * {@link FenixFramework#isDomainObjectValid(DomainObject)}
 * 
 */
class InMemBackEnd extends JVSTMBackEnd {

    public InMemBackEnd() {
        super(new NoRepository());
    }

    @Override
    public boolean isDomainObjectValid(DomainObject object) {
        return object != null;
    }

}