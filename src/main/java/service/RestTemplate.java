package service;

import com.intellij.openapi.components.ServiceManager;
import org.glassfish.jersey.client.ClientConfig;
import request.Auth;
import response.TranslateResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 19.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public interface RestTemplate {
    Optional<TranslateResponse> requestTranslate(String requestBody, Auth auth);
    String createRequestData(LanguageChecker languageChecker, String text) throws UnsupportedEncodingException;

    default WebTarget createWebTarget(String url) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target(url);
    }

    default String translate(String text, Auth auth) throws UnsupportedEncodingException {
        LanguageChecker languageChecker = ServiceManager.getService(LanguageChecker.class);
        String requestBody = createRequestData(languageChecker, text);
        return requestTranslate(requestBody, auth)
                .map(TranslateResponse::getTranslatedText)
                .orElse(text);
    }

}
