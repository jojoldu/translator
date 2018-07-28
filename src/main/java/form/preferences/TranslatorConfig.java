package form.preferences;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 8.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Getter
@Setter
@NoArgsConstructor
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
}
