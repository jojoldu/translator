package service.impl;

import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.Auth;
import request.azure.AzureRequestParameter;
import request.azure.AzureToken;
import response.TranslateResponse;
import response.azure.AzureResponse;
import service.AzureRestTemplate;
import service.LanguageChecker;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 3.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class AzureRestTemplateImpl implements AzureRestTemplate {
    private static final Logger logger = LoggerFactory.getLogger(AzureRestTemplateImpl.class);

    private static final String TOKEN_URL = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken?Subscription-Key=";
    private static final String TRANSLATE_URL = "https://api.microsofttranslator.com/V2/Http.svc/Translate?";

    private AzureToken azureToken;

    @Override
    public String createRequestData(LanguageChecker languageChecker, String text) throws UnsupportedEncodingException {
        String from = languageChecker.detect(text);

        return AzureRequestParameter.Builder.builder()
                .text(text)
                .from(from)
                .to(languageChecker.exchange(from))
                .build()
                .toUrlParameter();
    }

    @Override
    public TranslateResponse requestTranslate(String requestBody, Auth auth) {
        String token = issueToken(auth.getAzure().getSecretKey());

        Response response = createWebTarget(TRANSLATE_URL+requestBody)
                    .request()
                    .header("Authorization", "Bearer "+token)
                    .get(Response.class);

        if(response.getStatus() != 200){
            logger.error("Translate Request Exception : {}", response.getStatusInfo().getReasonPhrase());
            return null;
        }

        return new AzureResponse(response.readEntity(String.class));
    }

    @Override
    public String issueToken (String secretKey) {
        LocalDateTime currentTime = LocalDateTime.now();

        if(azureToken == null || azureToken.isExpired(currentTime)){
            String token = requestToken(secretKey);
            cacheToken(currentTime, token);
            return token;
        }

        return azureToken.getToken();
    }

    private String requestToken(String secretKey) {
        Response response = createWebTarget(TOKEN_URL+ secretKey)
                .request()
                .post(Entity.entity("", MediaType.TEXT_PLAIN_TYPE), Response.class);

        if(response.getStatus() == 200){
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Issue Token Exception : " + response.getStatusInfo().getReasonPhrase());
        }
    }

    private void cacheToken(LocalDateTime createTime, String token) {
        azureToken = new AzureToken(createTime, token);
    }

}
