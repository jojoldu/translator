package request;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 21.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class Auth {

    private Naver naver;

    private Auth() {}

    private Auth(Naver naver) {
        this.naver = naver;
    }

    public static Auth newNaverInstance(String clientId, String clientSecret) {
        return new Auth(new Naver(clientId, clientSecret));
    }

    public Naver getNaver() {
        return naver;
    }

    public static class Naver {
        public static final String HEADER_CLIENT_ID="X-Naver-Client-Id";
        public static final String HEADER_CLIENT_SECRET="X-Naver-Client-Secret";

        private String clientId;
        private String clientSecret;

        public Naver(String clientId, String clientSecret) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }

        public String getClientId() {
            return clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }
    }
}
