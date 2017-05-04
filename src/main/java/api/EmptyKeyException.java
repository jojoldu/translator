package api;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 4.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class EmptyKeyException extends RuntimeException{

    private static final String MESSAGE = "Empty API Key \nPreferences -> Tools -> Translator";

    public EmptyKeyException() {
        super(MESSAGE);
    }
}
