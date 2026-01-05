package com.tazz.adventofcode.y2015.day9;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class WeightedGraph {
    private Map<City, List<Edge>> adjencyList;

    public WeightedGraph() {
        adjencyList = new HashMap<>();
    }

    public void addVertex(City city) {
        adjencyList.putIfAbsent(city, new ArrayList<>());
    }

    public void addEdge(City a, City b, int km, boolean uniform) {
        adjencyList.putIfAbsent(a, new ArrayList<>());
        adjencyList.putIfAbsent(b, new ArrayList<>());
        adjencyList.get(a).add(new Edge(b, km));
        if (!uniform) {
            adjencyList.get(b).add(new Edge(a, km));
        }
    }

    public int shortestHamiltonianPath() {
        List<City> cities = new ArrayList<>(adjencyList.keySet());

        var dist = buildDistanceMap();

        int[] best = { Integer.MAX_VALUE,  Integer.MIN_VALUE};
        permute(cities, 0, dist, best);
        return best[0];
    }

    public int longestHamiltonianPath() {
        List<City> cities = new ArrayList<>(adjencyList.keySet());

        var dist = buildDistanceMap();

        int[] best = { Integer.MAX_VALUE,  Integer.MIN_VALUE};
        permute(cities, 0, dist, best);
        return best[1];
    }

    private Map<City, Map<City, Integer>> buildDistanceMap() {
        Map<City, Map<City, Integer>> dist = new HashMap<>();

        for (var entry : adjencyList.entrySet()) {
            City from = entry.getKey();
            dist.putIfAbsent(from, new HashMap<>());
            for (Edge e : entry.getValue()) {
                dist.get(from).put(e.city(), e.km());
            }
        }

        return dist;
    }

    private static void permute(List<City> cities, int idx, Map<City, Map<City, Integer>> dist, int[] best) {
        if (idx == cities.size()) {
            int total = shortestLength(cities, dist, best[0]);
            if (total < best[0]) {
                best[0] = total;
            }
            total = longestLength(cities, dist, best[1]);
            if (total > best[1]) {
                best[1] = total;
            }
            return;
        }

        for (int i = idx; i < cities.size(); i++) {
            Collections.swap(cities, idx, i);
            permute(cities, idx + 1, dist, best);
            Collections.swap(cities, idx, i);
        }
    }

    private static int shortestLength(
            List<City> route,
            Map<City, Map<City, Integer>> dist,
            int currentBest
    ) {
        int sum = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            City a = route.get(i);
            City b = route.get(i + 1);

            Integer w = dist.getOrDefault(a, Map.of()).get(b);
            if (w == null) return Integer.MAX_VALUE; // not connected (shouldn't happen in AoC input)

            sum += w;

            // pruning: abandon if already worse than best
            if (sum >= currentBest) return Integer.MAX_VALUE;
        }
        return sum;
    }

    private static int longestLength(
            List<City> route,
            Map<City, Map<City, Integer>> dist,
            int currentBest
    ) {
        int sum = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            City a = route.get(i);
            City b = route.get(i + 1);

            Integer w = dist.getOrDefault(a, Map.of()).get(b);
            if (w == null) return Integer.MIN_VALUE; // not connected (shouldn't happen in AoC input)

            sum += w;
        }
        // pruning: abandon if already worse than best
        if (sum <= currentBest) return Integer.MIN_VALUE;

        return sum;
    }
}
