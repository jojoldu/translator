package util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 9.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class LanguageCheckerTest {

    private LanguageChecker checker = new LanguageChecker();

    @Test
    public void 영문을_입력하면_en이_리턴된다() throws Exception {
        //given
        String str = "test";

        //then
        assertThat(checker.detect(str), is("en"));
    }

    @Test
    public void 한글을_입력하면_ko이_리턴된다() throws Exception {
        //given
        String str = "한글이에요";

        //then
        assertThat(checker.detect(str), is("ko"));
    }

    @Test
    public void en과ko는_서로_교환된다() throws Exception {
        //given
        String en = "en";
        String ko = "ko";

        //then
        assertThat(checker.exchange(en), is("ko"));
        assertThat(checker.exchange(ko), is("en"));
    }
}
