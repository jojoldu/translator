package api;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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

    @Test
    public void 토큰은_생성시간에서_8분이지나면_만료된다() throws Exception {
        //given
        LocalDateTime createTime = LocalDateTime.of(2017,5,4,0,0,0);
        LocalDateTime beforeTime = LocalDateTime.of(2017, 5, 4, 0, 7, 59);
        LocalDateTime afterTime = LocalDateTime.of(2017,5,4,0,8,1);

        AzureToken token = new AzureToken(createTime, "토큰");

        //then
        assertThat(token.isExpired(beforeTime), is(false));
        assertThat(token.isExpired(afterTime), is(true));


    }

    @Test
    public void 토큰요청시_이전요청보다_8분이지났을때만_새로발급한다() throws Exception {
        RestApi restApi = new RestApi();
        AzureToken token;
    }

}
