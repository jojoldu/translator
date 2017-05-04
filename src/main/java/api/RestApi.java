package api;

import config.AppConfig;
import org.glassfish.jersey.client.ClientConfig;

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

    private static final String TOKEN_URL = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken?Subscription-Key=";
    private static final String TRANSLATE_URL = "https://api.microsofttranslator.com/V2/Http.svc/Translate?";

    private AzureToken azureToken;

    public String translate(TranslateRequest requestData){
        Response response = createWebTarget(TRANSLATE_URL+requestData.toString())
                .request()
                .header("Authorization", "Bearer "+issueToken())
                .get(Response.class);

        if(response.getStatus() == 200){
            return removeXmlTag(response.readEntity(String.class));
        } else {
            throw new RuntimeException("Translate Request Exception : " + response.getStatusInfo().getReasonPhrase());
        }
    }

    private String removeXmlTag(String target){
        return target.replaceAll("(<.*\">)|(</.*>)", "");
    }

    public String issueToken () {
        LocalDateTime currentTime = LocalDateTime.now();

        if(azureToken == null || azureToken.isExpired(currentTime)){
            String token = requestToken();
            setAzureToken(currentTime, token);
            return token;
        }

        return azureToken.getToken();

    }

    private String requestToken() {
        Response response = createWebTarget(TOKEN_URL+ AppConfig.getSecretKey())
                .request()
                .post(Entity.entity("", MediaType.TEXT_PLAIN_TYPE), Response.class);

        if(response.getStatus() == 200){
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Issue Token Exception : " + response.getStatusInfo().getReasonPhrase());
        }
    }

    private void setAzureToken(LocalDateTime createTime, String token) {
        azureToken = new AzureToken(createTime, token);
    }

    private WebTarget createWebTarget(String REQUEST_URL) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target(REQUEST_URL);
    }


}
