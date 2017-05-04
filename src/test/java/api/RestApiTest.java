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
    public void 토큰은_생성시간에서_10분이지나면_만료된다() throws Exception {
        //given
        LocalDateTime createTime = LocalDateTime.of(2017,5,4,0,0,0);
        LocalDateTime beforeTime = LocalDateTime.of(2017, 5, 4, 0, 9, 59);
        LocalDateTime afterTime = LocalDateTime.of(2017,5,4,0,10,1);

        AzureToken token = new AzureToken(createTime, "토큰");

        //then
        assertThat(token.isExpired(beforeTime), is(false));
        assertThat(token.isExpired(afterTime), is(true));
    }

    @Test
    public void 번역요청을하면_문자열이_전달된다() throws Exception {
        //given
        RestApi restApi = new RestApi();
        TranslateRequest requestData = TranslateRequest.Builder.builder()
                .text("brother")
                .from("en")
                .to("ko")
                .build();

        String result = restApi.translate(requestData);

        //then
        assertThat(result, is("동생"));
        assertTrue(result.length() > 0);
    }

}
