package com.edusancon.wewac.promise;

public interface EventEmitter {
    void subscribe(SingleEventNotifier notifier);
}
