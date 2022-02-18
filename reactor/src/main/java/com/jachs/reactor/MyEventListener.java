package com.jachs.reactor;

import java.util.List;

public interface MyEventListener<T> {

        void onDataChunk(List<T> chunk);
        void processComplete();

}
