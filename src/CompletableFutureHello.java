//https://gist.github.com/piyusht007/87cf95102349f20c34f275257750470e

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.junit.Assert.*;

public class CompletableFutureHello {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("starting");
        Future<String> completableFuture = calculateAsync();

        String result = completableFuture.get();
        assertEquals("Hello", result);

    }

    public static Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });



        return completableFuture;
    }
}