package com.jachs.reactor.flux;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/***
 * @author zhanchaohan
 */
public class PublishOn_4_5_1 {

    @Test
    public void test1(){
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .publishOn(s)

                .map(i -> "value " + i);
        new Thread(() -> flux.subscribe(System.out::println));

        flux.blockLast(Duration.ofSeconds(3000));
    }
}
