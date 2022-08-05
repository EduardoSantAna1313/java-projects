package dependency.injection.component.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.*;

import dependency.injection.component.Dictionary;

@Component
public class DictionaryImpl implements Dictionary {

    private List<String> fWords = new ArrayList<>(Arrays.asList("osgi", "eclipse", "equinox"));

    private String fLanguage = "en_US";

    @Override
    public String getLanguage() {
        return fLanguage;
    }

    @Override
    public boolean check(String word) {
        return fWords.contains(word);
    }

    @Override
    public String toString() {
        return fLanguage;
    }

}
