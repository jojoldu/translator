package api;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 3.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class RestApiTest {

    @Test
    public void Azure에_토큰발급요청시_문자열토큰발급() throws Exception{
        RestApi restApi = new RestApi();
        String token = restApi.issueToken();

        assertTrue(token.length() > 0);
    }

}
