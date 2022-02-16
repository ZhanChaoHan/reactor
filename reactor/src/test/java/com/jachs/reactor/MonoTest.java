package com.jachs.reactor;

import reactor.core.publisher.Mono;

/***
 * 功能大致同Flux,不过只会存在一个值或空值
 * @author zhanchaohan
 * @see https://projectreactor.io/docs/core/release/reference/index.html
 */
public class MonoTest {

	public void test1() {
		Mono.just("你")
		.subscribe(System.out::println);
		
		Mono.justOrEmpty(null)
		.subscribe(System.out::println);
		
		
	}
}
