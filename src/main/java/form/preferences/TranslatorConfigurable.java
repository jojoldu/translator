package form.preferences;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
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

    private TranslatorConfigurableGUI gui;

    @SuppressWarnings("FieldCanBeLocal")
    private final Project project;

    public TranslatorConfigurable(@NotNull Project project) {
        this.project = project;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        gui = new TranslatorConfigurableGUI();
        gui.createUI(project);
        return gui.getRootPanel();
    }

    @Override
    public void disposeUIResources() {
        gui = null;
    }

    @NotNull
    @Override
    public String getId() {
        return "preference.TranslatorConfigurable";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Translator";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "preference.TranslatorConfigurable";
    }

    @Override
    public boolean isModified() {
        return gui.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        gui.apply();
    }
}
