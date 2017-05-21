package action;

import action.impl.Translator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.components.ServiceManager;
import component.Selector;
import config.ApiType;
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

        ApiType apiType = classifyType();
        Auth auth = createAuth(e, apiType);
        String selectedText = new Selector(e).getSelectedText();

        // 초기값 세팅
        init(e);

        // 비동기 Action 실행
        CompletableFuture.supplyAsync(() -> requestTranslate(selectedText, auth, apiType))
                .thenAccept(translatedText -> {
                    loadingComponent.hide();
                    action(selectedText, translatedText);
                });
    }

    private ApiType classifyType(){
        return ApiType.AZURE;
    }

    private Auth createAuth(AnActionEvent e, ApiType apiType) {
        TranslatorConfig config = TranslatorConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT));

        if(ApiType.NAVER == apiType){
            String clientId = AppConfig.getNaverClientId();
            String clientSecret = AppConfig.getNaverClientSecret();
            return Auth.newNaverInstance(clientId, clientSecret);
        } else {
            String secretKey = StringUtils.isNotEmpty(config.getAzureSecretKey())? config.getAzureSecretKey(): AppConfig.getSecretKey();
            return Auth.newAzureInstance(secretKey);
        }
    }

    private String requestTranslate(String text, Auth auth, ApiType apiType) {
        RestTemplate restTemplate = apiType.getRestTemplate();

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
