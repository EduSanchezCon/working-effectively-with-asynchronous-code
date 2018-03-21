package com.edusancon.wewac;

import com.edusancon.wewac.util.ThreadHelper;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

public class CompositionTest {

    @Test
    public void CompositionQuiz(){

        final CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            ThreadHelper.sleep(1000);
            System.out.println( "primer futuro" );
        });

        future1.thenRunAsync( () -> {
            ThreadHelper.sleep(1000);
            System.out.println( "segundo futuro" );
        });

        future1.thenRunAsync( () -> {
            ThreadHelper.sleep(1000);
            System.out.println( "tercer futuro" );
        });

        ThreadHelper.sleep(3500);
    }
}
