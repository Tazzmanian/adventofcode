package com.tazz.adventofcode.y2015.day13;

import java.util.*;

public class WeightedGraph {

    private static final String MYSELF = "myself";

    private Map<String, Map<String, Integer>> adjencyList;

    public WeightedGraph() {
        adjencyList = new HashMap<>();
    }

    public void addEdge(Neighbor neighbor) {
        adjencyList.putIfAbsent(neighbor.person1(), new HashMap<>());
        adjencyList.get(neighbor.person1()).put(neighbor.person2(), neighbor.likes());
    }

    public int greatestLikes() {
        var likes = buildLikesMap();
        int[] best = {Integer.MIN_VALUE};

        permute(new ArrayList<>(adjencyList.keySet()), 0, likes, best);

        return best[0];
    }

    public int greatestLikesWithMyself() {
        var likes = buildLikesMap();
        adjustLikes(likes);
        int[] best = {Integer.MIN_VALUE};
        var sits = new HashSet<>(adjencyList.keySet());
        sits.add(MYSELF);

        permute(new ArrayList<>(sits), 0, likes, best);

        return best[0];
    }

    private void adjustLikes(Map<String, Map<String, Integer>> likes) {
        likes.putIfAbsent(MYSELF, new HashMap<>());
        for(var person1 : likes.keySet()) {
            likes.get(person1).put(MYSELF, 0);
            likes.get(MYSELF).put(person1, 0);
        }
    }

    private void permute(List<String> sits, int idx, Map<String, Map<String, Integer>> likes, int[] best) {
        if (idx == sits.size()) {
            int total = highestLikes(sits, likes, best[0]);
            if (total > best[0]) {
                best[0] = total;
            }
            return;
        }

        for (int i = idx; i < sits.size(); i++) {
            Collections.swap(sits, idx, i);
            permute(sits, idx + 1, likes, best);
            Collections.swap(sits, idx, i);
        }
    }

    private int highestLikes(List<String> sits, Map<String, Map<String, Integer>> likes, int best) {
        int sum = 0;
        for (int i = 0; i < sits.size() - 1; i++) {
            String person1 = sits.get(i);
            String person2 = sits.get(i + 1);

            Integer like = likes.getOrDefault(person1, Map.of()).get(person2);
            if (like == null) {
                return Integer.MIN_VALUE;
            }

            sum += like;
        }

        String person1 = sits.getFirst();
        String person2 = sits.getLast();
        Integer like = likes.getOrDefault(person1, Map.of()).get(person2);
        sum += like;

        return sum > best ? sum : Integer.MIN_VALUE;
    }

    private Map<String, Map<String, Integer>> buildLikesMap() {
        var likes = new HashMap<String, Map<String, Integer>>();
        for (var person1 : adjencyList.keySet()) {
            likes.putIfAbsent(person1, new HashMap<>());
            for (var data : adjencyList.get(person1).entrySet()) {
                String person2 = data.getKey();
                var opposite = adjencyList.get(person2).get(person1);
                int totalLikes = data.getValue() + opposite;
                likes.get(person1).put(person2, totalLikes);
                likes.putIfAbsent(person2, new HashMap<>());
                likes.get(person2).put(person1, totalLikes);
            }
        }

        return likes;
    }
}
