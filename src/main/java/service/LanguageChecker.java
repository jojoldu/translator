package service;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 11.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public interface LanguageChecker {
    String detect(String text);

    String exchange(String language);
}
