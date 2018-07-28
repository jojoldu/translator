package response.azure;

import lombok.AllArgsConstructor;
import response.TranslateResponse;
import util.MessageConverter;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 21.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@AllArgsConstructor
public class AzureResponse implements TranslateResponse{

    private String xmlResult;

    @Override
    public String getTranslatedText() {
        return MessageConverter.removeXmlTag(xmlResult);
    }
}
