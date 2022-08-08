package dependency.injection.service.impl;

import java.util.Set;

import org.osgi.service.component.annotations.Component;

import dependency.injection.service.DictionaryService;

@Component
public class EnglishDictionaryServiceImpl implements DictionaryService {

    private static final String LANGUAGE = "en_US";

    private Set<String> words = Set.of("Hello", "World");

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
