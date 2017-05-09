package api;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 8.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class MessageConverter {

    private static final String camelCaseRegex = "([a-z])([A-Z]+)";

    public String convert(String target) {
        return target
                .replaceAll(camelCaseRegex, "$1 $2")
                .replaceAll("_", " ");
    }
}
