package service.impl;

import com.intellij.openapi.components.ServiceManager;
import dto.AzureToken;
import dto.TranslateRequest;
import org.glassfish.jersey.client.ClientConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.LanguageChecker;
import service.RestTemplate;
import util.MessageConverter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 3.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class RestTemplateImpl implements RestTemplate {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateImpl.class);

    private static final String TOKEN_URL = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken?Subscription-Key=";
    private static final String TRANSLATE_URL = "https://api.microsofttranslator.com/V2/Http.svc/Translate?";
    private static final String TRANSLATE_ALL_URL = "https://api.microsofttranslator.com/V2/Http.svc/GetTranslations?";
    private static final Integer MAX_TRANSLATION_SIZE = 5;

    private AzureToken azureToken;

    @Override
    public String translateSingleResult(String text, String secretKey) throws UnsupportedEncodingException {
        String token = issueToken(secretKey);
        TranslateRequest requestData = createRequestData(text, null);

        return MessageConverter.removeXmlTag(requestTranslateSingleResult(requestData, token));
    }

    @Override
    public List<String> translateMultiResults(String text, String secretKey) throws UnsupportedEncodingException {
        String token = issueToken(secretKey);

        TranslateRequest requestData = createRequestData(text, MAX_TRANSLATION_SIZE);
//        return requestTranslateMultiResults(requestData, token).stream()
//                .map(MessageConverter::removeXmlTag)
//                .collect(Collectors.toList());
        return null;
    }


    @NotNull
    private TranslateRequest createRequestData(String text, Integer size) throws UnsupportedEncodingException {
        LanguageChecker languageChecker = ServiceManager.getService(LanguageChecker.class);
        String from = languageChecker.detect(text);

        return TranslateRequest.Builder.builder()
                .text(text)
                .from(from)
                .to(languageChecker.exchange(from))
                .maxTranslations(size)
                .build();
    }

    private String requestTranslateSingleResult(TranslateRequest requestData, String token) {
        Response response = createWebTarget(TRANSLATE_URL+requestData.toString())
                    .request()
                    .header("Authorization", "Bearer "+token)
                    .get(Response.class);

        if(response.getStatus() != 200){
            logger.error("Translate Request Exception : {}", response.getStatusInfo().getReasonPhrase());
            return "";
        }

        return response.readEntity(String.class);
    }

    public String requestTranslateMultiResults(TranslateRequest requestData, String token) {

        Response response = createWebTarget(TRANSLATE_ALL_URL+requestData.toString())
                .request()
                .header("Authorization", "Bearer "+token)
                .post(Entity.entity("", MediaType.TEXT_PLAIN_TYPE), Response.class);

        if(response.getStatus() != 200){
            logger.error("Translate Request Multi Results Exception : {}", response.getStatusInfo().getReasonPhrase());
            return "";
        }

        return response.readEntity(String.class);
    }


    private WebTarget createWebTarget(String url) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target(url);
    }

    @Override
    public String issueToken (String secretKey) {
        LocalDateTime currentTime = LocalDateTime.now();

        if(azureToken == null || azureToken.isExpired(currentTime)){
            String token = requestToken(secretKey);
            cache(currentTime, token);
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

    private void cache(LocalDateTime createTime, String token) {
        azureToken = new AzureToken(createTime, token);
    }

}
