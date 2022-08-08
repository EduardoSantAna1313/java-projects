package dependency.injection.service.command;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import dependency.injection.service.DictionaryService;

@Component
public class ServiceComponent implements CommandProvider {

    @Reference
    private DictionaryService service;

    public void _dict(final CommandInterpreter ci) {

        final String arg = ci.nextArgument();

        System.out.println(service + " contains word " + arg + " ? " + service.check(arg));
    }

    public String getHelp() {
        return "";
    }

}