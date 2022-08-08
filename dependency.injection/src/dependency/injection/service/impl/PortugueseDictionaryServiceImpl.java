package dependency.injection.service.impl;

import java.util.Set;

import org.osgi.service.component.annotations.Component;

import dependency.injection.service.DictionaryService;

@Component
public class PortugueseDictionaryServiceImpl implements DictionaryService {

    private static final String LANGUAGE = "pt_BR";

    private Set<String> words = Set.of("Olá", "Mundo");

    @Override
    public boolean check(String word) {
        return words.contains(word);
    }

    @Override
    public String getLanguage() {
        return LANGUAGE;
    }

    @Override
    public String toString() {
        return "Dictionary " + getLanguage();
    }

}
