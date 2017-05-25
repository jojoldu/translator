package ui.icon;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.util.ui.AnimatedIcon;
import org.jetbrains.annotations.Nullable;
import component.Selector;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 12.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class LoadingComponent {

    private AnActionEvent e;
    private Balloon balloon;

    public LoadingComponent(@Nullable AnActionEvent e) {
        this.e = e;
    }

    private JComponent createPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        AnimatedIcon loadingIcon = new LoadingIcon();
        loadingIcon.resume();
        panel.add(loadingIcon, BorderLayout.CENTER);
        return panel;
    }

    public void showBalloon(){
        Selector selector = new Selector(e);
        JComponent jComponent = selector.getCurrentComponent();
        Point point = selector.extractPoint();

        if (jComponent != null && point != null) {
            balloon = JBPopupFactory.getInstance()
                    .createBalloonBuilder(createPanel())
                    .createBalloon();

            balloon.show(new RelativePoint(jComponent, point),
                            Balloon.Position.below);
        }
    }

    public void hideBalloon(){
        balloon.hide();
    }

}
