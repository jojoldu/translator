package action;

import api.RestApi;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import config.BeanFactory;
import config.Messages;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import preferences.TranslatorConfig;
import util.MessageConverter;
import util.PopupLoader;
import util.Selector;

import java.io.UnsupportedEncodingException;

/**
 * Created by jojoldu@gmail.com on 2017. 4. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class Translator extends AnAction {

    private static final Logger logger = LoggerFactory.getLogger(Translator.class);

    private RestApi restApi = BeanFactory.getRestApi();

    private TranslatorConfig config;
    private String secretKey;

    @Override
    public void actionPerformed(AnActionEvent e) {
        config = TranslatorConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT));
        secretKey = config.getAzureSecretKey();

        if(StringUtils.isEmpty(secretKey)){
            PopupLoader.show(Messages.EMPTY_KEY, e);
        }else{
            requestTranslate(e);
        }
    }

    private void requestTranslate(AnActionEvent event) {

        String text = MessageConverter.convert(Selector.getSelectedMessage(event));

        try {
            String translatedText = restApi.translate(text, secretKey);

            if(StringUtils.isNotBlank(translatedText)){
                PopupLoader.show(translatedText.trim(), event);
            }

        } catch (UnsupportedEncodingException e) {
            logger.error("requestTranslate : {}", e.getMessage());
        }
    }

}
