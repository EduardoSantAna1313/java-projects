package dependency.injection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Run he program as an OSGi Framework. Command on OSGI console: dict WORD
 */
public class Activator implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;
    }

    public void stop(BundleContext bundleContext) throws Exception {
        Activator.context = null;
    }

}
