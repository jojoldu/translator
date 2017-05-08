package ui;

import com.intellij.openapi.project.Project;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 8.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class TranslatorConfigurableGUI {
    private JPanel rootPanel;
    private JTextField azureSecretKeyField;
    private TranslatorConfig config;

    public TranslatorConfigurableGUI() {
    }

    public void createUI(Project project) {
        config = TranslatorConfig.getInstance(project);
        azureSecretKeyField.setText(config.getAzureSecretKey());
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JTextField getAzureSecretKeyField() {
        return azureSecretKeyField;
    }


    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setRequestFocusEnabled(true);
        final JLabel label1 = new JLabel();
        label1.setText("Azure Secret Key");
        rootPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(80, 16), null, 0, false));
        azureSecretKeyField = new JTextField();
        azureSecretKeyField.setAutoscrolls(true);
        azureSecretKeyField.setEditable(true);
        azureSecretKeyField.setEnabled(true);
        azureSecretKeyField.setHorizontalAlignment(10);
        rootPanel.add(azureSecretKeyField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        label1.setLabelFor(azureSecretKeyField);
    }

    public void apply() {
        config.setAzureSecretKey(azureSecretKeyField.getText());
    }

    public boolean isModified() {
        return !StringUtils.equals(azureSecretKeyField.getText(), config.getAzureSecretKey());
    }
}
