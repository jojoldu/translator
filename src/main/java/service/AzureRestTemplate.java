package service;

import java.io.UnsupportedEncodingException;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 11.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public interface AzureRestTemplate {
    String translate(String text, String secretKey) throws UnsupportedEncodingException;
    String issueToken (String secretKey);
}
