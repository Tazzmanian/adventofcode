package com.tazz.adventofcode.y2015.day17.consumer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.tazz.adventofcode.y2015.day17.shared.Task;

@Component
public class WorkScheduler {

    private final ExecutorService pool = Executors.newFixedThreadPool(8);

    public void submit(Task task) {
        int n = task.containers().size();
        int maxMask = 1 << n;

        for (int mask = 0; mask < maxMask; mask++) {
            final int currentMask = mask;
            pool.submit(() -> {
                if (matches(currentMask, task)) {
                    System.out.println("Found match: " + currentMask);
                }
            });
        }
    }

    private boolean matches(int mask, Task task) {
        int sum = 0;
        List<Integer> containers = task.containers();

        for (int i = 0; i < containers.size(); i++) {
            if ((mask & (1 << i)) != 0) {
                sum += containers.get(i);
            }
        }

        return sum == task.target();
    }
}
