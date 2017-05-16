package util;

import org.jetbrains.annotations.NotNull;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 8.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class MessageConverter {

    private static final String CAMEL_CASE_REGEX = "([a-z])([A-Z]+)";

    public static String convert(String target) {
        return target
                .replaceAll(CAMEL_CASE_REGEX, "$1 $2")
                .replaceAll("_", " ");
    }

    private static final String XML_REGEX = "(<.*\">)|(</.*>)";

    public static String removeXmlTag(String target){
        return target.replaceAll(XML_REGEX, "");
    }

    @NotNull
    public static String applyStyle(String text, String translatedText) {
        //DarkOrange
        String title = "<p style='color:#FF8C00;font-size:16px'><strong>" + text + "</strong></p>";
        String content = "<p style='color:#F8F8FF;font-size:12px'>" + translatedText + "</p>";
        return title+content;
    }

}
