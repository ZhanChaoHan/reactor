package com.jachs.reactor;

import java.util.List;

public class MyEventListenerImpl implements  MyEventListener{
    @Override
    public void onDataChunk(List chunk) {
        System.out.println("onDataChunk:\t"+chunk.size());
    }

    @Override
    public void processComplete() {
        System.out.println("完成");
    }

    public void register(MyEventListener<String> stringMyEventListener) {
        System.out.println("register");
    }
}
