package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;
import form.dialog.TranslateDialog;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class DialogTranslateAction extends AnAction implements TranslateAction {

    private static final String TITLE = "Translation";

    @Override
    public void actionPerformed(AnActionEvent e) {
        TranslateDialog dialog = new TranslateDialog();
        dialog.pack();
        dialog.setTitle(TITLE);
        dialog.setLocationRelativeTo(WindowManager.getInstance().getFrame(e.getProject()).getRootPane().getParent());
        dialog.setVisible(true);

    }
}
