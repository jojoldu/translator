package api;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 3.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class RestApi {
    private static final Logger logger = LoggerFactory.getLogger(RestApi.class);

    private static final String TOKEN_URL = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken?Subscription-Key=";
    private static final String TRANSLATE_URL = "https://api.microsofttranslator.com/V2/Http.svc/Translate?";

    private AzureToken azureToken;

    public String translate(TranslateRequest requestData, String secretKey){
        String token = issueToken(secretKey);
        return removeXmlTag(requestTranslate(requestData, token));
    }

    private String requestTranslate(TranslateRequest requestData, String token) {
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

    private WebTarget createWebTarget(String url) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target(url);
    }

    private String removeXmlTag(String target){
        return target.replaceAll("(<.*\">)|(</.*>)", "");
    }

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
