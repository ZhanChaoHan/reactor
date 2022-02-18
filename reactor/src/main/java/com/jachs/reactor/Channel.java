package com.jachs.reactor;

/***
 * @author zhanchaohan
 */
public class Channel {

    public void cancel() {
        System.out.println("cancel");
    }

    public void close() {
        System.out.println("close");
    }

    public void poll(long n) {
        System.out.println("poll"+n);
    }
}
