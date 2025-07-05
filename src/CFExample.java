//https://gist.github.com/piyusht007/87cf95102349f20c34f275257750470e
package com.tomtom.postal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CFExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("starting");
        final int addNumbersTill = 2000;
        sum(addNumbersTill);
    }

    private static void sum(int addNumbersTill) throws ExecutionException, InterruptedException {
        final int batchSize = 200;
        final int batches = addNumbersTill % batchSize == 0 ? addNumbersTill / batchSize : addNumbersTill / batchSize + 1;
        int from = 1;

        final List<CompletableFuture<Integer>> allTasks = new ArrayList<>();

        // 1. Submit the batches
        for (int i = 0; i < batches; i++) {
            allTasks.add(submitTasks(from, batchSize));
            from += batchSize;
        }

        // 2. Get all the batches (in form of CompletableFutures)
        final CompletableFuture<List<Integer>> listCompletableFuture = CompletableFuture.allOf(allTasks.stream().toArray(CompletableFuture[]::new))
                .thenApply(future -> allTasks.stream()
                        .map(CompletableFuture::join)
                        .flatMap(Stream::of)
                        .collect(Collectors.toList()));

        // 3. Accumulate the result from all the batches
        System.out.println(listCompletableFuture.get().stream().reduce(Integer::sum).get());
    }

    private static CompletableFuture<Integer> submitTasks(int from, int batchSize) {
        return CompletableFuture.supplyAsync(() -> IntStream.range(from, from + batchSize).boxed().reduce(Integer::sum).get());
    }
}