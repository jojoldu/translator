package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.components.ServiceManager;
import config.AppConfig;
import ui.LoadingComponent;
import util.constant.Messages;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import preferences.TranslatorConfig;
import service.RestTemplate;
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

    private TranslatorConfig config;
    private String secretKey;
    private LoadingComponent loadingComponent;

    @Override
    public void actionPerformed(AnActionEvent e) {
        config = TranslatorConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT));
        secretKey = config.getAzureSecretKey();
        loadingComponent = new LoadingComponent(e);
        loadingComponent.show();

        if(StringUtils.isEmpty(secretKey)){
            secretKey = AppConfig.getSecretKey();
            requestTranslate(e);
            //PopupLoader.show(Messages.EMPTY_KEY, e);
        }else{
            requestTranslate(e);
        }

        loadingComponent.hide();
    }

    private void requestTranslate(AnActionEvent event) {

        String text = MessageConverter.convert(Selector.getSelectedMessage(event));
        RestTemplate restTemplate = ServiceManager.getService(RestTemplate.class);
        try {
            String translatedText = restTemplate.translate(text, secretKey);

            if(StringUtils.isNotBlank(translatedText)){
                PopupLoader.show(translatedText.trim(), event);
            }

        } catch (UnsupportedEncodingException e) {
            logger.error("requestTranslate : {}", e.getMessage());
        }
    }

}
