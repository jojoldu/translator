package form.dialog;

import javax.swing.*;
import java.awt.event.*;

public class TranslateDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTextField textField1;
    private JButton query;
    private JLabel translatedText;

    public TranslateDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(query);

        query.addActionListener(e -> onQuery());

        buttonCancel.addActionListener(e -> onCancel());

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
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TranslateDialog dialog = new TranslateDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
