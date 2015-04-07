package org.fenixedu.bennu.notifications.test.ff;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;

public class FenixFrameworkRunner extends BlockJUnit4ClassRunner {

    static {
        if (!FenixFramework.isInitialized()) {
            FenixFramework.initialize(new InMemConfig());
        }
    }

    public FenixFrameworkRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    @Atomic(mode = TxMode.WRITE)
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        super.runChild(method, notifier);
    }

}
