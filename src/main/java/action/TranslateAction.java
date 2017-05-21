package action;

import action.impl.Translator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.components.ServiceManager;
import component.Selector;
import config.AppConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import preferences.TranslatorConfig;
import request.Auth;
import service.AzureRestTemplate;
import service.RestTemplate;
import ui.LoadingComponent;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 17.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public abstract class TranslateAction extends AnAction{

    private static final Logger logger = LoggerFactory.getLogger(Translator.class);

    private LoadingComponent loadingComponent;

    @Override
    public void actionPerformed(AnActionEvent e) {

        loadingComponent = new LoadingComponent(e);
        loadingComponent.show();

        Auth auth = createAuth(e);

        // 초기값 세팅
        init(e);

        String selectedText = new Selector(e).getSelectedText();

        // 비동기 Action 실행
        CompletableFuture.supplyAsync(() -> requestTranslate(selectedText, auth))
                .thenAccept(translatedText -> {
                    action(selectedText, translatedText);
                    loadingComponent.hide();
                });
    }

    private Auth createAuth(AnActionEvent e) {
        TranslatorConfig config = TranslatorConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT));
        String secretKey = StringUtils.isNotEmpty(config.getAzureSecretKey())? config.getAzureSecretKey(): AppConfig.getSecretKey();

        Auth auth = Auth.newAzureInstance(secretKey);

        return auth;
    }

    private String requestTranslate(String text, Auth auth) {
        RestTemplate restTemplate = ServiceManager.getService(AzureRestTemplate.class);
        try {
            return restTemplate.translate(text, auth);
        } catch (UnsupportedEncodingException e) {
            logger.error("requestTranslate : {}", e.getMessage());
        }

        return text;
    }

    protected abstract void init(AnActionEvent e);

    protected abstract void action(String text, String translatedText);
}
