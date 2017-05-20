package action.impl;

import action.TranslateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import ui.LoadingComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import component.PopupLoader;

/**
 * Created by jojoldu@gmail.com on 2017. 4. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class Translator extends TranslateAction {
    private static final Logger logger = LoggerFactory.getLogger(Translator.class);

    private LoadingComponent loadingComponent;
    private PopupLoader popupLoader;

    @Override
    protected void init(AnActionEvent e) {
        popupLoader = new PopupLoader(e);
        loadingComponent = new LoadingComponent(e);
        loadingComponent.show();
    }

    @Override
    protected void action(String text, String translatedText) {
        popupLoader.show(text.trim(), translatedText.trim());
        loadingComponent.hide();
    }
}
