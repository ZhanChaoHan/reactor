package com.jachs.reactor.flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.Test;

import reactor.core.publisher.Flux;

/***
 * Flux 是一个发出(emit)0-N个元素组成的异步序列的Publisher<T>,<br>
 * 可以被onComplete信号或者onError信号所终止。<br>
 * 在响应流规范中存在三种给下游消费者调用的方法 onNext, onComplete, 和onError。<br>
 * @author zhanchaohan
 * @see https://projectreactor.io/docs/core/release/reference/index.html
 */
public class FluxTest {
	private void printFlux(Flux flux,String type) {
		flux.subscribe((a)->{
			System.out.println(a+"\t"+type);
		});
	}
	@Test
	public void test1() throws InterruptedException {
		//静态方法初始化
		printFlux(Flux.just("一段文字"),"just");
		printFlux(Flux.fromArray(new String[] {"a","b"}),"fromArray");
		printFlux(Flux.fromIterable(Arrays.asList("i","t","e","m")),"fromIterable");
		printFlux(Flux.fromStream(Stream.of("abc")),"fromStream");
		//
		printFlux(Flux.empty(),"empty");//创建一个Flux，完成而不发射任何项目。
		printFlux(Flux.error(new Exception("报个异常")),"error");//创建一个Flux，它在订阅之后立即以指定的错误终止。
		printFlux(Flux.never(),"never");//创建一个Flux，它永远不会发出任何数据、错误或完成信号。
		printFlux(Flux.range(2, 20),"range");//建立一个Flux，它只会发出一个count递增整数的序列，从start开始。也就是说，在start（包含）和start + count（排除）之间发出整数，然后完成。
		//创建一个Flux，它以0开始发射长值并递增全局计时器上指定的时间间隔。如果需求没有及时产生，一个OnError将用来发出信号。IllegalStateException详细说明无法发出的信息。在正常情况下，Flux将永远不会完成。
		printFlux(Flux.interval(Duration.ofMillis(1000), Duration.ofSeconds(3)),"interval");
	
		//防止程序过早退出，放一个CountDownLatch拦住
		CountDownLatch latch = new CountDownLatch(1);
		latch.await();
	}
	/***
	 * map是纯元素转换</br>
	 * 
	 */
	@Test
	public void mapAndFlatMap() {
		 Flux.just(1, 2, 3, 4)
         .log()
         .map(i -> {
             try {
                 TimeUnit.SECONDS.sleep(1);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             return i * 2;
         })
         .subscribe(System.out::println);
	}
	/**
	 * 这里的flatMap，将元素转为Mono或Flux，转换操作里头还可以进行异步操作
	 * 
	 */
	@Test
	public void flatMap() throws InterruptedException {
		Flux.just(1,2,3,4)
        .log()
        .flatMap(e -> {
            return Flux.just(e*5).delayElements(Duration.ofSeconds(1));
        })
        .subscribe(System.out::println);
		TimeUnit.SECONDS.sleep(10);
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
		//
		String []sList=new String[] {"A","for",""};
		Flux.create((a)->{
			List<String>scList=new ArrayList<String>();
			for (int kk = 0; kk < sList.length; kk++) {
				scList.add(sList[kk]);
			}
			a.complete();
		}).subscribe(System.out::println);
	}

}
