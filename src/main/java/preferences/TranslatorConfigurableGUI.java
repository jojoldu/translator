package preferences;

import com.intellij.openapi.project.Project;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
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
    private JComboBox apiType;
    private JTextField azureSecretKeyField;
    private JTextField naverClientIdField;
    private JTextField naverClientSecretField;
    private JLabel azureSecretKeyTitle;
    private JLabel naverClientIdTitle;
    private JLabel naverClientSecretTitle;
    private TranslatorConfig config;

    public TranslatorConfigurableGUI() {
    }

    public void createUI(Project project) {
        config = TranslatorConfig.getInstance(project);

        apiType.setSelectedItem(config.getApiType());

        azureSecretKeyField.setText(config.getAzureSecretKey());

        naverClientIdField.setText(config.getNaverClientId());
        naverClientSecretField.setText(config.getNaverClientSecret());
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setRequestFocusEnabled(true);
        rootPanel.add(azureSecretKeyTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(80, 16), null, 0, false));
        rootPanel.add(naverClientIdTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(80, 16), null, 0, false));
        rootPanel.add(naverClientSecretTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(80, 16), null, 0, false));

        azureSecretKeyField = new JTextField();
        naverClientIdField = new JTextField();
        naverClientSecretField = new JTextField();

        setEnabled();

        rootPanel.add(azureSecretKeyField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rootPanel.add(naverClientIdField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rootPanel.add(naverClientSecretField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

        azureSecretKeyTitle.setLabelFor(azureSecretKeyField);
    }

    private void setEnabled(){

        azureSecretKeyField.setAutoscrolls(true);
        azureSecretKeyField.setEditable(true);
        azureSecretKeyField.setEnabled(true);
        azureSecretKeyField.setHorizontalAlignment(10);

        naverClientIdField.setAutoscrolls(true);
        naverClientIdField.setEditable(true);
        naverClientIdField.setEnabled(true);
        naverClientIdField.setHorizontalAlignment(10);

        naverClientSecretField.setAutoscrolls(true);
        naverClientSecretField.setEditable(true);
        naverClientSecretField.setEnabled(true);
        naverClientSecretField.setHorizontalAlignment(10);
    }

    public void apply() {

        config.setAzureSecretKey(azureSecretKeyField.getText());

        config.setNaverClientId(naverClientIdField.getText());
        config.setNaverClientSecret(naverClientSecretField.getText());
    }

    public boolean isModified() {

        if(isModifiedApiType()){
            return true;
        }

        if(isModifiedAzureSecretKey()){
            return true;
        }

        if(isModifiedNaverClientId()){
            return true;
        }

        if(isModifiedNaverClientSecret()){
            return true;
        }

        return false;
    }

    private boolean isModifiedApiType() {
        return !StringUtils.equals(apiType.getSelectedItem().toString(), config.getApiType());
    }

    private boolean isModifiedAzureSecretKey() {
        return !StringUtils.equals(azureSecretKeyField.getText(), config.getAzureSecretKey());
    }

    private boolean isModifiedNaverClientId() {
        return !StringUtils.equals(naverClientIdField.getText(), config.getNaverClientId());
    }

    private boolean isModifiedNaverClientSecret() {
        return !StringUtils.equals(naverClientSecretField.getText(), config.getNaverClientSecret());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
