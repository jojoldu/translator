package request;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 21.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class Auth {

    private Azure azure;
    private Naver naver;

    private Auth() {}

    private Auth(Azure azure) {
        this.azure = azure;
    }

    private Auth(Naver naver) {
        this.naver = naver;
    }

    public static Auth newAzureInstance(String secretKey) {
        return new Auth(new Azure(secretKey));
    }

    public static Auth newNaverInstance(String clientId, String clientSecret) {
        return new Auth(new Naver(clientId, clientSecret));
    }

    public Azure getAzure() {
        return azure;
    }

    public Naver getNaver() {
        return naver;
    }

    public static class Azure {
        private String secretKey;

        public Azure(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getSecretKey() {
            return secretKey;
        }
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
