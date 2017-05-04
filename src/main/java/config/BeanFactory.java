package config;

import api.RestApi;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 4.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class BeanFactory {

    private static RestApi REST_API = new RestApi();

    public static RestApi getRestApi() {
        return REST_API;
    }
}
