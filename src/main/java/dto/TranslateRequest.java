package dto;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 4.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */


public class TranslateRequest {
    private String text;
    private String from;
    private String to;
    private Integer maxTranslations;

    private TranslateRequest(String text, String from, String to) {
        this.text = text;
        this.from = from;
        this.to = to;
    }

    public TranslateRequest(String text, String from, String to, Integer maxTranslations) {
        this.text = text;
        this.from = from;
        this.to = to;
        this.maxTranslations = maxTranslations;
    }

    @Override
    public String toString() {

        String param = "text=" + text + "&from=" + from + "&to=" + to;
        if(maxTranslations != null){
            param+="&maxTranslations="+maxTranslations;
        }
        return param;
    }

    public static final class Builder {
        private String text;
        private String from;
        private String to;
        private Integer maxTranslations;

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

        public Builder maxTranslations(Integer maxTranslations){
            this.maxTranslations = maxTranslations;
            return this;
        }

        public TranslateRequest build() {
            if(maxTranslations == null){
                return new TranslateRequest(text, from, to);
            }

            return new TranslateRequest(text, from, to, maxTranslations);
        }
    }
}
