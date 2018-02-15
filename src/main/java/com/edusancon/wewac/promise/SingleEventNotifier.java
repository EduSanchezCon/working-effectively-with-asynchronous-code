package com.edusancon.wewac.promise;

import java.util.concurrent.CompletableFuture;

public class SingleEventNotifier {

    final CompletableFuture<EventInfo> futureEvent;

    public SingleEventNotifier(EventEmitter emitter) {
        this.futureEvent = new CompletableFuture<>();
        emitter.subscribe(this);
    }

    public CompletableFuture<EventInfo> ask(){
        return futureEvent;
    }

    public void onEventReceived(EventInfo event){
        futureEvent.complete(event);
    }
}
