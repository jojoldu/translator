package config;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 4.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class TranslatorConfigurable implements SearchableConfigurable {

    @NotNull
    @Override
    public String getId() {
        return "preference.TranslatorConfigurable";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Translator Plugin";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "preference.TranslatorConfigurable";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return null;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }
}
