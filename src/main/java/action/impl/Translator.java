package action.impl;

import action.InstantTranslateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import component.PopupLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jojoldu@gmail.com on 2017. 4. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Slf4j
public class Translator extends InstantTranslateAction {
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
