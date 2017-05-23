package action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import config.ApiType;
import config.AppConfig;
import exception.EmptyAuthException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import preferences.TranslatorConfig;
import request.Auth;
import service.RestTemplate;

import java.io.UnsupportedEncodingException;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public interface TranslateAction {

    Logger logger = LoggerFactory.getLogger(TranslateAction.class);

    default ApiType classifyType(AnActionEvent e) {
        String apiTypeKey = TranslatorConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT)).getApiType();
        return ApiType.findByName(apiTypeKey);
    }

    default Auth createAuth(AnActionEvent e, ApiType apiType) throws EmptyAuthException {
        TranslatorConfig config = TranslatorConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT));

        if (ApiType.NAVER == apiType) {
            String clientId = config.getNaverClientId();
            String clientSecret = config.getNaverClientSecret();

            verifyAuth(clientId, clientSecret);

            return Auth.newNaverInstance(clientId, clientSecret);
        } else if (ApiType.AZURE == apiType) {
            verifyAuth(config.getAzureSecretKey());
            return Auth.newAzureInstance(config.getAzureSecretKey());
        } else {
            return Auth.newAzureInstance(AppConfig.getSecretKey());
        }
    }

    default void verifyAuth(String secretKey) {
        if (StringUtils.isEmpty(secretKey)) {
            throw new EmptyAuthException("AZURE의 SecretKey가 없습니다.");
        }
    }

    default void verifyAuth(String clientId, String clientSecret) {
        if (StringUtils.isEmpty(clientId) || StringUtils.isEmpty(clientSecret)) {
            throw new EmptyAuthException("NAVER의 ClientId와 ClientSecret가 없습니다.");
        }
    }

    default String requestTranslate(String text, Auth auth, ApiType apiType) {
        RestTemplate restTemplate = apiType.getRestTemplate();

        try {
            return restTemplate.translate(text, auth);
        } catch (UnsupportedEncodingException e) {
            logger.error("requestTranslate : {}", e.getMessage());
        }

        return text;
    }
}
