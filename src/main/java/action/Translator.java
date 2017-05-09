package action;

import api.RestApi;
import util.LanguageChecker;
import util.MessageConverter;
import api.TranslateRequest;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.awt.RelativePoint;
import config.BeanFactory;
import config.Messages;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import preferences.TranslatorConfig;
import ui.LoadingIcon;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;

/**
 * Created by jojoldu@gmail.com on 2017. 4. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class Translator extends AnAction {

    private static final Logger logger = LoggerFactory.getLogger(Translator.class);

    private RestApi restApi = BeanFactory.getRestApi();
    private MessageConverter messageConverter = BeanFactory.getMessageConverter();
    private LanguageChecker languageChecker = BeanFactory.getLanguageChecker();

    private TranslatorConfig config;
    private String secretKey;
    private LoadingIcon loadingIcon;

    @Override
    public void actionPerformed(AnActionEvent event) {
        config = TranslatorConfig.getInstance(event.getRequiredData(CommonDataKeys.PROJECT));
        secretKey = config.getAzureSecretKey();
        loadingIcon = new LoadingIcon();

        if(StringUtils.isEmpty(secretKey)){
            showPopup(Messages.EMPTY_KEY, event);
        }else{
            requestTranslate(event);
        }
    }

    private void requestTranslate(AnActionEvent event) {

        String text = messageConverter.convert(getSelectedMessage(event));

        try {
            String translatedText = restApi.translate(createRequestData(text), secretKey);

            if(StringUtils.isNotBlank(translatedText)){
                showPopup(translatedText.trim(), event);
            }

        } catch (UnsupportedEncodingException e) {
            logger.error("requestTranslate : {}", e.getMessage());
        }
    }

    @NotNull
    private TranslateRequest createRequestData(String text) throws UnsupportedEncodingException {
        String from = languageChecker.detect(text);

        return TranslateRequest.Builder.builder()
                .text(text)
                .from(from)
                .to(languageChecker.exchange(from))
                .build();
    }

    private void showPopup(String message, AnActionEvent e){
        JComponent jComponent = getCurrentComponent(e);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        Point point = extractPoint(editor);

        if(jComponent != null && point != null){
            JBPopupFactory.getInstance()
                    .createHtmlTextBalloonBuilder(toWhiteText(message), loadIcon(), Color.GRAY, null)
                    .setFadeoutTime(7500)
                    .createBalloon()
                    .show(new RelativePoint(jComponent, point),
                            Balloon.Position.below);
        }
    }

    @NotNull
    private String toWhiteText(String message) {
        return "<span style='color:white;'>" + message + "</span>";
    }

    private Icon loadIcon(){
        return IconLoader.getIcon("/icons/translate.png");
    }

    @Nullable
    private Point extractPoint(Editor editor) {
        Point point = null;

        if (editor != null) {
            point = editor.visualPositionToXY(makeSelectionPosition(editor));
        }

        return point;
    }

    private static final int LINE_INTERVAL = 1;

    private VisualPosition makeSelectionPosition(Editor editor) {
        VisualPosition start = editor.getSelectionModel().getSelectionStartPosition();
        VisualPosition end = editor.getSelectionModel().getSelectionEndPosition();
        int line = 0;
        int column = 0;

        if (end != null && start != null) {
            line = end.getLine() + LINE_INTERVAL;
            column = start.getColumn() + ((end.column - start.getColumn())/2);
        }

        return new VisualPosition(line, column);
    }

    private JComponent getCurrentComponent(AnActionEvent e){
        Editor editor = PlatformDataKeys.EDITOR.getData(e.getDataContext());

        if (editor != null) {
            return editor.getContentComponent();
        }

        return null;
    }

    private String getSelectedMessage(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (editor != null) {
            return editor.getSelectionModel().getSelectedText();
        }

        return "";
    }
}
