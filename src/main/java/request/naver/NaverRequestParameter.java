package request.naver;

import request.RequestParameter;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 20.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class NaverRequestParameter implements RequestParameter{

    private String source;
    private String target;
    private String text;

    public NaverRequestParameter(String source, String target, String text) {
        this.source = source;
        this.target = target;
        this.text = text;
    }

    @Override
    public String toUrlParameter() {
        return "source="+source+"&target="+target+"&text="+text;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getText() {
        return text;
    }

    public static Builder builder() {
        return Builder.builder();
    }

    public static final class Builder {
        private String source;
        private String target;
        private String text;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder target(String target) {
            this.target = target;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public NaverRequestParameter build() {
            return new NaverRequestParameter(source, target, text);
        }
    }
}
