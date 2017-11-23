package request.azure;

import request.RequestParameter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 4.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */


public class AzureRequestParameter implements RequestParameter {
    private String text;
    private String from;
    private String to;

    private AzureRequestParameter(String text, String from, String to) {
        this.text = text;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toUrlParameter() {
        return "text="+text+"&from="+from+"&to="+to;
    }

    public static Builder builder() {
        return Builder.builder();
    }

    public static final class Builder {
        private String text;
        private String from;
        private String to;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder text(String text) throws UnsupportedEncodingException {
            this.text = URLEncoder.encode(text, "UTF-8");
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public AzureRequestParameter build() {
            return new AzureRequestParameter(text, from, to);
        }
    }
}
