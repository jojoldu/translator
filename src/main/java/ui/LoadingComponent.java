package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.AnimatedIcon;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 12.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class LoadingComponent extends DialogWrapper{

    {
        init();
    }

    public LoadingComponent(@Nullable Project project) {
        super(project);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        AnimatedIcon loadingIcon = new LoadingIcon();
        loadingIcon.resume();
        panel.add(loadingIcon, BorderLayout.CENTER);
        return panel;
    }
}
