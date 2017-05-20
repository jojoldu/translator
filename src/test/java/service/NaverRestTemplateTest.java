package service;

import org.junit.Test;
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
        NaverRestTemplate restTemplate = new NaverRestTemplateImpl();
        String text = "번역";
        String translatedText = restTemplate.translate(text);

        //then
        assertThat(translatedText, is("translate"));

    }
}
