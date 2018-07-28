package request.azure;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 3.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Getter
@AllArgsConstructor
public class AzureToken {

    private static final long INTERVAL_TIME = 10;

    private LocalDateTime createTime;
    private String token;


    public boolean isExpired(LocalDateTime currentTime){
        return currentTime.isAfter(createTime.plusMinutes(INTERVAL_TIME));
    }
}
