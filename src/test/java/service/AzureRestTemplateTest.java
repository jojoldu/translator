package service;

import config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import request.Auth;
import request.azure.AzureRequestParameter;
import request.azure.AzureToken;
import response.TranslateResponse;
import service.impl.AzureRestTemplateImpl;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 3.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class AzureRestTemplateTest {

    private Auth auth;

    @Before
    public void setup() {
        auth = Auth.newAzureInstance(AppConfig.getSecretKey());
    }

    @Test
    public void Azure에_토큰발급요청시_문자열토큰발급() throws Exception{
        AzureRestTemplateImpl restApi = new AzureRestTemplateImpl();
        String token = restApi.issueToken(AppConfig.getSecretKey());

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
        AzureRestTemplateImpl restApi = new AzureRestTemplateImpl();
        String requestBody = AzureRequestParameter.Builder.builder()
                .from("en")
                .to("ko")
                .text("brother")
                .build()
                .toUrlParameter();
        TranslateResponse result = restApi.requestTranslate(requestBody, auth);

        //then
        assertThat(result.getTranslatedText(), is("동생"));
    }

    @Test
    public void 한글번역요청을하면_영문자열이_전달된다() throws Exception {
        //given
        AzureRestTemplateImpl restApi = new AzureRestTemplateImpl();
        String requestBody = AzureRequestParameter.Builder.builder()
                .from("ko")
                .to("en")
                .text("결제 승인")
                .build()
                .toUrlParameter();
        TranslateResponse result = restApi.requestTranslate(requestBody, auth);

        //then
        assertThat(result.getTranslatedText(), is("Payment approval"));
    }
}
