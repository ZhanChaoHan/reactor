package com.jachs.reactor.flux;

import org.junit.Test;
import reactor.core.publisher.Flux;

/***
 * @author zhanchaohan
 */
public class HandlingErrors_4_6 {
    @Test
    public void test1(){
        Flux.just(1, 2, 0)
                .map(i -> "100 / " + i + " = " + (100 / i)) //this triggers an error with 0
                .onErrorReturn("Divided by zero :(") // error handling example
                .subscribe(System.out::println);

    }
    @Test
    public void test2(){
        Flux<String> s = Flux.range(1, 10)
                .map(v -> doSomethingDangerous(v))
                .map(v -> doSecondTransform(v));
        s.subscribe(value -> System.out.println("RECEIVED " + value),
                error -> System.err.println("CAUGHT " + error)
        );
    }
    @Test
    public void test3(){
        try {
            for (int i = 1; i < 11; i++) {
                String v1 = doSomethingDangerous(i);
                String v2 = doSecondTransform(v1);
                System.out.println("RECEIVED " + v2);
            }
        } catch (Throwable t) {
            System.err.println("CAUGHT " + t);
        }
    }

    private String doSecondTransform(Object v) {
        System.out.println("doSecondTransform:\t"+v);
        return v.toString();
    }
    private String doSomethingDangerous(int v) {
        System.out.println("doSecondTransform:\t"+v);
        return v+"";
    }
    private Object doSomethingDangerous(Integer v) {
        System.out.println("doSomethingDangerous:\t"+v);
        return v;
    }
}
