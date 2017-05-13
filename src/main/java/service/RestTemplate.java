package service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 11.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public interface RestTemplate {
    String translateSingleResult(String text, String secretKey) throws UnsupportedEncodingException;
    List<String> translateMultiResults(String text, String secretKey) throws UnsupportedEncodingException;
    String issueToken (String secretKey);
}
