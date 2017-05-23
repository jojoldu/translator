package config;

import com.intellij.openapi.components.ServiceManager;
import service.AzureRestTemplate;
import service.NaverRestTemplate;
import service.RestTemplate;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 21.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public enum ApiType {
    AZURE (ServiceManager.getService(AzureRestTemplate.class)),
    NAVER (ServiceManager.getService(NaverRestTemplate.class));

    private RestTemplate restTemplate;

    ApiType(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
