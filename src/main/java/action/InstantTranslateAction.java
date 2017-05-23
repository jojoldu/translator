package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import component.PopupLoader;
import component.Selector;
import config.ApiType;
import exception.EmptyAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.Auth;
import ui.LoadingComponent;

import java.util.concurrent.CompletableFuture;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 17.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public abstract class InstantTranslateAction extends AnAction implements TranslateAction {

    private static final Logger logger = LoggerFactory.getLogger(InstantTranslateAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {

        LoadingComponent loadingComponent = new LoadingComponent(e);
        loadingComponent.show();

        ApiType apiType = classifyType(e);

        try{
            Auth auth = createAuth(e, apiType);
            String selectedText = new Selector(e).getSelectedText();

            // 초기값 세팅
            init(e);

            // 비동기 Action 실행
            CompletableFuture.supplyAsync(() -> requestTranslate(selectedText, auth, apiType))
                    .thenAccept(translatedText -> {
                        loadingComponent.hide();
                        action(selectedText, translatedText);
                    });

        } catch (EmptyAuthException eae){
            loadingComponent.hide();
            new PopupLoader(e).showError(eae.getMessage());
        }
    }

    protected abstract void init(AnActionEvent e);

    protected abstract void action(String text, String translatedText);
}
