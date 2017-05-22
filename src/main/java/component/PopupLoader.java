package component;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.awt.RelativePoint;
import util.MessageConverter;

import javax.swing.*;
import java.awt.*;

public class PopupLoader {

    private Selector selector;
    private final JComponent jComponent;
    private final Point point;

    public PopupLoader(AnActionEvent event) {
        this.selector = new Selector(event);
        this.jComponent = selector.getCurrentComponent();
        this.point = selector.extractPoint();

    }

    public void show(String text, String translatedText) {
        if (isSelected()) {
            JBPopupFactory.getInstance()
                    .createHtmlTextBalloonBuilder(
                            MessageConverter.applyTranslateStyle(text, translatedText),
                            null,
                            Color.GRAY,
                            null)
                    .setFadeoutTime(7500)
                    .createBalloon()
                    .show(new RelativePoint(jComponent, point),
                            Balloon.Position.below);
        }
    }

    public void showError(String content){
        if(isSelected()){
            JBPopupFactory.getInstance()
                    .createHtmlTextBalloonBuilder(
                            MessageConverter.applyExceptionStyle(content),
                            null,
                            Color.GRAY,
                            null)
                    .setFadeoutTime(7500)
                    .createBalloon()
                    .show(new RelativePoint(jComponent, point),
                            Balloon.Position.below);

        }
    }

    private boolean isSelected() {
        return jComponent != null && point != null;
    }
}