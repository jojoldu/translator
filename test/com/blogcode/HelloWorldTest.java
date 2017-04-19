package com.blogcode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2017. 4. 20.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class HelloWorldTest {

    @Test
    public void hello() {
        String str = "hello";
        assertThat("hello", is(str));
    }
}
