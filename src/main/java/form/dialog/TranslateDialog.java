package form.dialog;

import action.TranslateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.util.ui.UIUtil;
import component.PopupLoader;
import component.Selector;
import config.ApiType;
import exception.EmptyAuthException;
import request.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CompletableFuture;

public class TranslateDialog extends JDialog implements TranslateAction {
    private JPanel contentPane;
    private JTextField queryTextField;
    private JButton queryBtn;
    private JPanel translatedPane;
    private JLabel translatedTextLabel;
    private JTextPane translatedTextPane;

    private static final String TITLE = "Translation";
    private ApiType apiType;
    private Auth auth;

    public TranslateDialog(ApiType apiType, Auth auth) {
        this.apiType = apiType;
        this.auth = auth;
        init();
        queryBtn.addActionListener(e -> onQuery());
    }

    public TranslateDialog(EmptyAuthException eae){
        init();
        translatedTextLabel.setText(eae.getMessage());
    }

    public void onShowing(AnActionEvent e){
        this.pack();
        this.setTitle(TITLE);
        this.setLocationRelativeTo(WindowManager.getInstance().getFrame(e.getProject()).getRootPane().getParent());

        applyColor();

        this.setVisible(true);
    }

    private void applyColor(){
        if(UIUtil.isUnderDarcula()){
            Color dracula = new Color(43,43,43);
            translatedPane.setBackground(dracula);
            queryTextField.setBackground(dracula);
            queryTextField.setForeground(Color.WHITE);
            translatedTextLabel.setForeground(Color.WHITE);
        } else {
            Color lowBlue = new Color(48, 99, 191);
            translatedPane.setBackground(Color.WHITE);
            queryTextField.setBackground(Color.WHITE);
            queryTextField.setForeground(lowBlue);
            translatedTextLabel.setForeground(lowBlue);
        }

    }

    private void init() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(queryBtn);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
                e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onQuery() {

        translatedTextLabel.setText("Querying....");

        String queryText = queryTextField.getText();

        // 비동기 Action 실행
        CompletableFuture.supplyAsync(() -> requestTranslate(queryText, auth, apiType))
                .thenAccept(translatedText -> translatedTextLabel.setText(translatedText));
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
