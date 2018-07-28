package request.naver;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import request.RequestParameter;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 20.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Builder
@Getter
@NoArgsConstructor
public class NaverRequestParameter implements RequestParameter {

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

}
