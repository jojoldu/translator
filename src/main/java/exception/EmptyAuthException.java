package exception;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 22.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class EmptyAuthException extends RuntimeException{
    public EmptyAuthException(String message) {
        super(message);
    }
}
