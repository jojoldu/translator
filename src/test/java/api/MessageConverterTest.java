package api;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 8.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class MessageConverterTest {

    private MessageConverter converter = new MessageConverter();

    @Test
    public void 카멜케이스는_space로_변경된다() throws Exception{
        //given
        String str1 = "getName";
        String str2 = "AzureApiKey";

        //then
        assertThat(converter.convert(str1), is("get Name"));
        assertThat(converter.convert(str2), is("Azure Api Key"));
    }

    @Test
    public void 언더스코어는_space로_변경된다() throws Exception {
        //given
        String str1 = "get_name";
        String str2 = "azure_api_key";

        //then
        assertThat(converter.convert(str1), is("get name"));
        assertThat(converter.convert(str2), is("azure api key"));
    }

    @Test
    public void 언더스코어와카멜케이스는_space로_변경된다() throws Exception {
        //given
        String str1 = "getName_method";
        String str2 = "azureApiKey_method_call";

        //then
        assertThat(converter.convert(str1), is("get Name method"));
        assertThat(converter.convert(str2), is("azure Api Key method call"));
    }
}
