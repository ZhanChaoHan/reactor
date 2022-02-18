package com.jachs.reactor.flux;

import org.junit.Test;
import reactor.core.publisher.Mono;

/***
 * @author zhanchaohan
 */
public class ThreadingSchedulers_4_5 {
    @Test
    public  void test1() throws  Exception{
        final Mono<String> mono = Mono.just("hello ");

        Thread t = new Thread(() -> mono
                .map(msg -> msg + "thread ")
                .subscribe(v ->
                        System.out.println(v + Thread.currentThread().getName())
                )
        );
        t.start();
        t.join();
    }
}
