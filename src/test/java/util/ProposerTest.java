package util;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 10.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class ProposerTest {
    private Proposer proposer = new Proposer();

    @Test
    public void 영문은_카멜케이스_언더스코어로_전환한_리스트를_반환한다() throws Exception {
        //given
        String val = "get order status";
        List<String> results = proposer.propose(val);

        //then
        assertThat(results.size(), is(4));
        assertThat(results.get(0), is("getOrderStatus"));
        assertThat(results.get(1), is("get_order_status"));
        assertThat(results.get(2), is("GetOrderStatus"));
        assertThat(results.get(3), is("GET_ORDER_STATUS"));
    }
    
}
