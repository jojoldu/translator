package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;
import config.ApiType;
import exception.EmptyAuthException;
import form.dialog.TranslateDialog;
import request.Auth;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class DialogTranslateAction extends AnAction implements TranslateAction {

    private static final String TITLE = "Translation";

    @Override
    public void actionPerformed(AnActionEvent e) {

        ApiType apiType = classifyType(e);
        Auth auth;
        TranslateDialog dialog;

        try {
            auth = createAuth(e, apiType);
            dialog = new TranslateDialog(apiType, auth);
        } catch (EmptyAuthException eae){
            dialog = new TranslateDialog(eae);
        }

        dialog.onShowing(e);
    }
}
