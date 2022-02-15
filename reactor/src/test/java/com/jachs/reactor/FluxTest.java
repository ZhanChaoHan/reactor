package com.jachs.reactor;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

import reactor.core.publisher.Flux;

/***
 * 
 * @author zhanchaohan
 *
 */
public class FluxTest {
	private void printFlux(Flux flux,String type) {
		flux.subscribe((a)->{
			System.out.println(a+"\t"+type);
		});
	}
	@Test
	public void test1() {
		//静态方法初始化
		printFlux(Flux.just("一段文字"),"just");
		printFlux(Flux.fromArray(new String[] {"a","b"}),"fromArray");
		printFlux(Flux.fromIterable(Arrays.asList("i","t","e","m")),"fromIterable");
		printFlux(Flux.fromStream(Stream.of("abc")),"fromStream");
		//
		printFlux(Flux.empty(),"empty");
		printFlux(Flux.error(new Exception("喜欢报个异常")),"error");
		printFlux(Flux.never(),"never");
		printFlux(Flux.range(2, 20),"range");
		printFlux(Flux.interval(Duration.ofMillis(1000), Duration.ofSeconds(3)),"interval");
	}
	@Test
	public void test2() {
		//generate,初始化
		Flux.generate(a->{
			a.next("hello");
			a.complete();
		}).subscribe(System.out::println);
		//create,初始化
		Flux.create(a->{
			for (int kk = 0; kk < 15; kk++) {
				a.next(kk);
			}
			a.complete();
		}).subscribe(System.out::println);
	}
}
