package action;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.notification.Notification;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import config.AppConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import preferences.TranslatorConfig;
import service.Proposer;
import service.RestTemplate;
import ui.LoadingComponent;
import util.MessageConverter;
import util.PopupLoader;
import util.Selector;
import util.constant.Messages;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 10.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class Replacer extends AnAction{
    private static final Logger logger = LoggerFactory.getLogger(Translator.class);

    private TranslatorConfig config;
    private String secretKey;

    @Override
    public void actionPerformed(AnActionEvent e) {
        config = TranslatorConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT));
        secretKey = config.getAzureSecretKey();

        if(StringUtils.isEmpty(secretKey)){
            secretKey = AppConfig.getSecretKey();
            suggest(e);
            //PopupLoader.show(Messages.EMPTY_KEY, e);
        }else{
            suggest(e);
        }
    }

    private void suggest(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);

        if (editor != null && editor.getProject() != null) {
            String text = requestTranslate(e);
            LookupManager.getInstance(editor.getProject()).showLookup(editor, getProposeList(text));
        }
    }

    private String requestTranslate(AnActionEvent event) {

        String text = MessageConverter.convert(Selector.getSelectedMessage(event));
        RestTemplate restTemplate = ServiceManager.getService(RestTemplate.class);

        try {
            return restTemplate.translate(text, secretKey);
        } catch (UnsupportedEncodingException e) {
            logger.error("requestTranslate : {}", e.getMessage());
        }

        return text;
    }

    private LookupElement[] getProposeList(String text){
        Proposer proposer = ServiceManager.getService(Proposer.class);

        return proposer.propose(text).stream()
                .map(LookupElementBuilder::create)
                .toArray(LookupElement[]::new);
    }
}
