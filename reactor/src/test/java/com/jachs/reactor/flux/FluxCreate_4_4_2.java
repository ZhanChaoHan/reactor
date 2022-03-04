package com.jachs.reactor.flux;

import com.jachs.reactor.Channel;
import com.jachs.reactor.MyEventListener;
import com.jachs.reactor.MyEventListenerImpl;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.List;

/***
 * @author zhanchaohan
 */
public class FluxCreate_4_4_2 {

    /***
     * 大多数反应堆操作人员，比如create，都遵循混合推拉模型。我们的意思是，尽管大多数处理都是异步的（建议采用推送方式），但它有一个小的拉动组件：请求。<br>
     * 消费者从源中提取数据，在第一次请求之前不会发出任何信息。无论何时数据可用，源都会将数据推送到消费者手中，但在其请求量的范围内。<br>
     * 请注意，push（）和create（）都允许设置onRequest使用者，以便管理请求量，并确保只有在存在挂起的请求时，数据才会被推送到接收器<br>
     */
    @Test
    public void test1(){
        MyEventListenerImpl myEventProcessor = new MyEventListenerImpl();

        Flux<String> bridge = Flux.create(sink -> {
            myEventProcessor.register(
                    new MyEventListener<String>() {
                        public void onDataChunk(List<String> chunk) {
                            for(String s : chunk) {
                                sink.next(s);
                            }
                        }
                        public void processComplete() {
                            sink.complete();
                        }
                    });
        });
    }

    /***
     * 两个回调onDispose和onCancel在取消或终止时执行任何清理。onDispose可用于在通量完成、<br>
     * 出错或取消时执行清理。onCancel可用于在使用onDispose清理之前执行任何特定于取消的操作。
     */
    @Test
    public void test2(){
        Channel channel=new Channel();
        Flux<String> bridge = Flux.create(sink -> {
            sink.onRequest(n -> channel.poll(n))
                    .onCancel(() -> channel.cancel())
                    .onDispose(() -> channel.close());
        });
    }

}
