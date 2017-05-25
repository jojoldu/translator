package form.dialog;

import javax.swing.*;
import java.awt.event.*;

public class TranslateDialog extends JDialog {
    private JPanel contentPane;
    private JTextField queryTextField;
    private JButton queryBtn;
    private JPanel translatedPane;
    private JLabel translatedTextLabel;
    private JTextPane translatedTextPane;

    public TranslateDialog() {
        init();
    }

    private void init() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(queryBtn);

        queryBtn.addActionListener(e -> onQuery());

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
        // add your code here
        translatedTextLabel.setText(queryTextField.getText());
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
