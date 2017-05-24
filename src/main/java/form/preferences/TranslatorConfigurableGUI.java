package form.preferences;

import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;

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

        setEnabled();
        apiType.addItemListener(e -> setEnabled());
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void setEnabled(){
        if(apiType.getSelectedItem().equals("AZURE")){
            useAzure();
        } else if(apiType.getSelectedItem().equals("NAVER")){
            useNaver();
        } else {
            useDefault();
        }
    }

    private void useDefault() {
        azureSecretKeyField.setEditable(false);
        azureSecretKeyField.setEnabled(false);

        naverClientIdField.setEditable(false);
        naverClientIdField.setEnabled(false);

        naverClientSecretField.setEditable(false);
        naverClientSecretField.setEnabled(false);
    }

    private void useAzure() {
        azureSecretKeyField.setEditable(true);
        azureSecretKeyField.setEnabled(true);

        naverClientIdField.setEditable(false);
        naverClientIdField.setEnabled(false);

        naverClientSecretField.setEditable(false);
        naverClientSecretField.setEnabled(false);
    }

    private void useNaver() {
        azureSecretKeyField.setEditable(false);
        azureSecretKeyField.setEnabled(false);

        naverClientIdField.setEditable(true);
        naverClientIdField.setEnabled(true);

        naverClientSecretField.setEditable(true);
        naverClientSecretField.setEnabled(true);
    }

    public void apply() {
        config.setApiType(apiType.getSelectedItem().toString());
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
