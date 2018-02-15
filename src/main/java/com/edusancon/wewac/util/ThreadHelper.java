package com.edusancon.wewac.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadHelper {

    public static final Executor APP_EXECUTOR = Executors.newFixedThreadPool(64);

    public static void sleep(long delay){

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
    }

    public static void block(long delay){
        Object object = new Object();
        try {
            synchronized ("h") {
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {}
    }
}
