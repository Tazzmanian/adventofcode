package com.tazz.adventofcode.y2015.day14;

import java.util.*;

public class StarCalculator {

    public static void awardPointsAtSecond(
            List<ReindeerStats> stats, int t, Map<String, Integer> points
    ) {
        int best = Integer.MIN_VALUE;

        // compute best distance at time t
        int[] dist = new int[stats.size()];
        for (int i = 0; i < stats.size(); i++) {
            int d = stats.get(i).distanceAfter(t);
            dist[i] = d;
            best = Math.max(best, d);
        }

        // award point to all leaders
        for (int i = 0; i < stats.size(); i++) {
            if (dist[i] == best) {
                points.merge(stats.get(i).name(), 1, Integer::sum);
            }
        }
    }
}
