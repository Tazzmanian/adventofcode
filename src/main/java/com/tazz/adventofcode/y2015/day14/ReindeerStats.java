package com.tazz.adventofcode.y2015.day14;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record ReindeerStats(String name, int speed, int runDuration, int restDuration) {

    public int distanceAfter(int seconds) {
        int totalDistance = 0;
//        log.info("=========== {} ===============", name);
        for (int i = 0; i < seconds; ) {
            var runTime = runDuration(i, seconds);
            totalDistance += runTime * speed;
            i += runTime;
//            log.info("Distance {} km after {} seconds", totalDistance, i);
            if (runTime < runDuration) {
                break;
            }
            i += restDuration;
//            log.info("Rest until {} seconds", i);
        }

        return totalDistance;
    }

    private int runDuration(int currentTime, int maxDuration) {
        return runDuration + currentTime >= maxDuration ? maxDuration - currentTime : runDuration;
    }
}
