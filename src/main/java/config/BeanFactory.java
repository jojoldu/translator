package config;

import api.RestApi;
import util.LanguageChecker;
import util.MessageConverter;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 4.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class BeanFactory {

    private static RestApi restApi = new RestApi();
    private static LanguageChecker languageChecker = new LanguageChecker();

    public static RestApi getRestApi() {
        return restApi;
    }

    public static LanguageChecker getLanguageChecker() {
        return languageChecker;
    }
}
