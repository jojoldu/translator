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

    private static final String TOKEN_URL = "https://api.cognitive.microsoft.com/sts/v1.0/requestToken?Subscription-Key=";

    private AzureToken azureToken;

    public String issueToken () {
        LocalDateTime currentTime = LocalDateTime.now();

        if(azureToken == null || azureToken.isExpired(currentTime)){
            String token = requestToken();
            setAzureToken(currentTime, token);
            return token;
        }

        return azureToken.getToken();

    }

    private void setAzureToken(LocalDateTime createTime, String token) {
        azureToken = new AzureToken(createTime, token);
    }

    private String requestToken() {
        final String REQUEST_URL = TOKEN_URL+ AppConfig.getSecretKey();
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(REQUEST_URL);

        Response response = target
                .request()
                .post(Entity.entity("", MediaType.TEXT_PLAIN_TYPE), Response.class);

        if(response.getStatus() == 200){
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Issue Token Exception : " +response.getStatusInfo().getReasonPhrase());
        }
    }




}
