package service;

import com.intellij.openapi.components.ServiceManager;
import org.glassfish.jersey.client.ClientConfig;
import org.jetbrains.annotations.NotNull;
import request.Auth;
import service.impl.LanguageCheckerImpl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.UnsupportedEncodingException;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 19.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public interface RestTemplate {
    String translate(String text, Auth auth) throws UnsupportedEncodingException;

    default WebTarget createWebTarget(String url) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target(url);
    }

    default String extractFrom(LanguageChecker languageChecker, String text){
        return languageChecker.detect(text);
    }

    default String exchangeLanguageType(LanguageChecker languageChecker, String languageType){
        return languageChecker.exchange(languageType);
    }
}
