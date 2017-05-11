package service;

import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 11.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public interface Proposer {
    List<String> propose(String text);
}
