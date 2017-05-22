package preferences;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 8.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@State(
        name="TranslatorConfig",
        storages = {
                @Storage("TranslatorConfig.xml")}
)
public class TranslatorConfig implements PersistentStateComponent<TranslatorConfig> {

    private String apiType = "";
    private String azureSecretKey = "";
    private String naverClientId = "";
    private String naverClientSecret = "";

    public TranslatorConfig() {}

    @Nullable
    @Override
    public TranslatorConfig getState() {
        return this;
    }

    @Override
    public void loadState(TranslatorConfig state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public static TranslatorConfig getInstance(Project project){
        return ServiceManager.getService(project, TranslatorConfig.class);
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    public String getAzureSecretKey() {
        return azureSecretKey;
    }

    public void setAzureSecretKey(String azureSecretKey) {
        this.azureSecretKey = azureSecretKey;
    }

    public String getNaverClientId() {
        return naverClientId;
    }

    public void setNaverClientId(String naverClientId) {
        this.naverClientId = naverClientId;
    }

    public String getNaverClientSecret() {
        return naverClientSecret;
    }

    public void setNaverClientSecret(String naverClientSecret) {
        this.naverClientSecret = naverClientSecret;
    }

}
