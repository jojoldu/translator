import com.intellij.codeInsight.highlighting.HighlightManager;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;
import sun.security.ssl.HandshakeInStream;

import javax.swing.*;

/**
 * Created by jojoldu@gmail.com on 2017. 4. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class TextChanger extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getProject();
        String message = getSelectedMessage(e);

//        Messages.showMessageDialog(project, message, "텍스트체인저", Messages.getInformationIcon());

        showFromStatusBar(e, String.valueOf(showCurrentCursor(e)));
    }

    private int showCurrentCursor(AnActionEvent e){
        Editor editor = PlatformDataKeys.EDITOR.getData(e.getDataContext());

        int offset = editor.getCaretModel().getOffset();

        return offset;
    }

/*
    private void showFromFrame(Project project, String message){
        JFrame jFrame = WindowManager.getInstance().getFrame(project);

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(message, MessageType.INFO, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getCenterOf(jFrame.getComponent()),
                        Balloon.Position.above);
    }
*/

    private void showFromStatusBar(AnActionEvent e, String message) {
        StatusBar statusBar = WindowManager.getInstance()
                .getStatusBar(PlatformDataKeys.PROJECT.getData(e.getDataContext()));

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(message, MessageType.INFO, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getCenterOf(statusBar.getComponent()),
                        Balloon.Position.atRight);
    }

    private void high(Project project){
        HighlightManager highlightManager = HighlightManager.getInstance(project);
    }

    private String getSelectedMessage(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        final SelectionModel selectionModel;

        if (editor != null) {
            selectionModel = editor.getSelectionModel();

            return selectionModel.getSelectedText();
        }

        return "";
    }

}
