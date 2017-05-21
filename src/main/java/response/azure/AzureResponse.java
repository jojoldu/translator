package response.azure;

import response.TranslateResponse;
import util.MessageConverter;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 21.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class AzureResponse implements TranslateResponse{

    private String xmlResult;

    public AzureResponse(String xmlResult) {
        this.xmlResult = xmlResult;
    }

    @Override
    public String getTranslatedText() {
        return MessageConverter.removeXmlTag(xmlResult);
    }
}
