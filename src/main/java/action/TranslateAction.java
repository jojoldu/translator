package action;

import action.impl.Translator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import component.PopupLoader;
import component.Selector;
import config.ApiType;
import config.AppConfig;
import exception.EmptyAuthException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import preferences.TranslatorConfig;
import request.Auth;
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

    @Override
    public void actionPerformed(AnActionEvent e) {

        LoadingComponent loadingComponent = new LoadingComponent(e);
        loadingComponent.show();

        ApiType apiType = classifyType(e);

        try{
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

        } catch (EmptyAuthException eae){
            loadingComponent.hide();
            new PopupLoader(e).showError(eae.getMessage());
        }

    }

    private ApiType classifyType(AnActionEvent e){
        return ApiType.valueOf(TranslatorConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT)).getApiType());
    }

    private Auth createAuth(AnActionEvent e, ApiType apiType) throws EmptyAuthException{
        TranslatorConfig config = TranslatorConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT));

        if(ApiType.NAVER == apiType){
            String clientId = config.getNaverClientId();
            String clientSecret = config.getNaverClientSecret();

            verifyAuth(clientId, clientSecret);

            return Auth.newNaverInstance(clientId, clientSecret);
        } else {
            String secretKey = StringUtils.isNotEmpty(config.getAzureSecretKey())? config.getAzureSecretKey(): AppConfig.getSecretKey();
            return Auth.newAzureInstance(secretKey);
        }
    }

    private void verifyAuth(String clientId, String clientSecret){
        if(StringUtils.isEmpty(clientId) || StringUtils.isEmpty(clientSecret)){
            throw new EmptyAuthException("NAVER의 ClientId와 ClientSecret가 없습니다.");
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
