package util;

import action.Translator;
import com.google.common.base.Optional;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 9.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class LanguageChecker {
    private static final Logger logger = LoggerFactory.getLogger(LanguageChecker.class);

    private LanguageDetector languageDetector;
    private TextObjectFactory textObjectFactory;

    public LanguageChecker() {
        //build language detector:
        try {
            languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                    .withProfiles(new LanguageProfileReader().readAllBuiltIn()) //load all languages:
                    .build();
        } catch (IOException e) {
            logger.error("LanguageChecker {}", e.getMessage());
        }

        //create a text object factory
        textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
    }

    public String detect(String text) {
        //query:
        TextObject textObject = textObjectFactory.forText(text);
        Optional<LdLocale> lang = languageDetector.detect(textObject);

        if(lang.isPresent()){
            return lang.get().getLanguage();
        }

        return "en";
    }

    public String exchange(String language) {

        if("en".equals(language)){
            return "ko";
        } else if("ko".equals(language)){
            return "en";
        }

        return "ko";
    }
}
