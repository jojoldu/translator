package request.azure;

import lombok.Builder;
import lombok.NoArgsConstructor;
import request.RequestParameter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 4.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@NoArgsConstructor
public class AzureRequestParameter implements RequestParameter {
    private String text;
    private String from;
    private String to;

    @Builder
    public AzureRequestParameter(String text, String from, String to) throws UnsupportedEncodingException {
        this.text = URLEncoder.encode(text, "UTF-8");
        this.from = from;
        this.to = to;
    }

    @Override
    public String toUrlParameter() {
        return "text="+text+"&from="+from+"&to="+to;
    }
}
