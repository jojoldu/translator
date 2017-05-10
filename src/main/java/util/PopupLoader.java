package util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.awt.RelativePoint;
import util.MessageConverter;
import util.Selector;
import util.SimpleIconLoader;

import javax.swing.*;
import java.awt.*;

public class PopupLoader {

    public static void show(String message, AnActionEvent e) {
        JComponent jComponent = Selector.getCurrentComponent(e);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        Point point = Selector.extractPoint(editor);

        if (jComponent != null && point != null) {
            JBPopupFactory.getInstance()
                    .createHtmlTextBalloonBuilder(
                            MessageConverter.toWhiteText(message),
                            SimpleIconLoader.loadTranslateIcon(),
                            Color.GRAY,
                            null)
                    .setFadeoutTime(7500)
                    .createBalloon()
                    .show(new RelativePoint(jComponent, point),
                            Balloon.Position.below);
        }
    }
}