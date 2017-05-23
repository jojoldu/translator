package action.impl;

import action.InstantTranslateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import component.PopupLoader;

/**
 * Created by jojoldu@gmail.com on 2017. 4. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class Translator extends InstantTranslateAction {
    private static final Logger logger = LoggerFactory.getLogger(Translator.class);

    private PopupLoader popupLoader;

    @Override
    protected void init(AnActionEvent e) {
        popupLoader = new PopupLoader(e);

    }

    @Override
    protected void action(String text, String translatedText) {
        popupLoader.show(text.trim(), translatedText.trim());
    }
}
