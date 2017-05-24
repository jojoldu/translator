package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import form.dialog.TranslateDialog;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class DialogTranslateAction extends AnAction implements TranslateAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        TranslateDialog dialog = new TranslateDialog();
        dialog.pack();
        dialog.setVisible(true);
    }
}
