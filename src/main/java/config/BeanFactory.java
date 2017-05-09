package config;

import api.RestApi;
import api.MessageConverter;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 4.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class BeanFactory {

    private static RestApi restApi = new RestApi();
    private static MessageConverter messageConverter = new MessageConverter();

    public static RestApi getRestApi() {
        return restApi;
    }

    public static MessageConverter getMessageConverter() {
        return messageConverter;
    }
}
