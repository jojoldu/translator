package service;

import config.AppConfig;
import org.junit.Test;
import request.Auth;
import response.naver.NaverResponse;
import service.impl.LanguageCheckerImpl;
import service.impl.NaverRestTemplateImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 19.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class NaverRestTemplateTest {

    @Test
    public void 한글_보내면_영문이_온다() throws Exception{
        //given
        NaverRestTemplateImpl restTemplate = new NaverRestTemplateImpl();
        Auth auth = Auth.newNaverInstance(AppConfig.getNaverClientId(), AppConfig.getNaverClientSecret());
        String requestBody = "source=ko&target=en&text=번역";
        NaverResponse result = restTemplate.requestTranslate(requestBody, auth);

        //then
        assertThat(result.getTranslatedText(), is("translation"));
    }
}
