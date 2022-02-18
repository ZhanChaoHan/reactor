package com.jachs.reactor.flux;

import com.jachs.reactor.SampleSubscriber;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/***
 * @author zhanchaohan
 */
public class BaseSubscriberTest_4_3_3 {

    @Test
    public void test1(){
        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(ss);
    }
}
