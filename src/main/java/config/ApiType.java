package config;

import com.intellij.openapi.components.ServiceManager;
import service.AzureRestTemplate;
import service.NaverRestTemplate;
import service.RestTemplate;

import java.util.Arrays;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 21.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public enum ApiType {
    DEFAULT (ServiceManager.getService(AzureRestTemplate.class)),
    AZURE (ServiceManager.getService(AzureRestTemplate.class)),
    NAVER (ServiceManager.getService(NaverRestTemplate.class));

    private RestTemplate restTemplate;

    ApiType(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public static ApiType findByName(String name){
        return Arrays.stream(ApiType.values())
                .filter(e -> e.name().equals(name))
                .findAny()
                .orElse(ApiType.DEFAULT);
    }
}
