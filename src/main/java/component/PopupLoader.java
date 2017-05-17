package component;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.awt.RelativePoint;
import component.Selector;
import util.MessageConverter;

import javax.swing.*;
import java.awt.*;

public class PopupLoader {

    private Selector selector;

    public PopupLoader(AnActionEvent event) {
        this.selector = new Selector(event);
    }

    public void show(String text, String translatedText) {
        JComponent jComponent = selector.getCurrentComponent();
        Point point = selector.extractPoint();

        if (jComponent != null && point != null) {
            JBPopupFactory.getInstance()
                    .createHtmlTextBalloonBuilder(
                            MessageConverter.applyStyle(text, translatedText),
                            null,
                            Color.GRAY,
                            null)
                    .setFadeoutTime(7500)
                    .createBalloon()
                    .show(new RelativePoint(jComponent, point),
                            Balloon.Position.below);
        }
    }
}