package action.impl;

import action.TranslateAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.components.ServiceManager;
import config.AppConfig;
import ui.LoadingComponent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import preferences.TranslatorConfig;
import service.RestTemplate;
import component.PopupLoader;
import component.Selector;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;

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
