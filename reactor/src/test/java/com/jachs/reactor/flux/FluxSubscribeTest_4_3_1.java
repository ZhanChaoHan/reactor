package com.jachs.reactor.flux;

import org.junit.Test;
import reactor.core.publisher.Flux;

/***
 * @author  zhanchaohan
 * @see https://projectreactor.io/docs/core/release/reference/index.html#core-features
 */
public class FluxSubscribeTest_4_3_1 {
    @Test
    public void test1(){
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe();
    }
    @Test
    public void test2(){
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(i -> System.out.println(i));
    }
    @Test
    public void test3(){
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error: " + error));
    }
    @Test
    public void test5(){
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));
    }
    @Test
    public void test6(){
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10));
    }
}
