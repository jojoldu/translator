package service.impl;

import com.google.common.base.CaseFormat;
import com.intellij.openapi.components.ServiceManager;
import service.LanguageChecker;
import service.Proposer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 10.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class ProposerImpl implements Proposer{

    public List<String> propose(String text) {
        if(isEnglish(text)){
            return createPropositions(text.replaceAll(" ", "_").toLowerCase());
        }

        return Collections.singletonList(text);
    }

    private boolean isEnglish(String text) {
        return ServiceManager.getService(LanguageChecker.class).detect(text).equals("en");
    }

    private List<String> createPropositions(String text){
        return Arrays.asList(
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, text),
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, text),
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, text),
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, text)
        );
    }

}
