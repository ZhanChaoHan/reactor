package com.jachs.reactor;

import java.util.List;

public class MyEventListenerImpl implements  MyEventListener{
    @Override
    public void onDataChunk(List chunk) {

    }

    @Override
    public void processComplete() {

    }

    public void register(MyEventListener<String> stringMyEventListener) {
    }
}
