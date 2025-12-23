package com.tazz.adventofcode.y2015.day6;

import lombok.Getter;

enum Command {
    ON("turn on") {
        public void execute(Point a, Point b, boolean[][] grid) {
            int x1 = Math.min(a.x(), b.x()), x2 = Math.max(a.x(), b.x());
            int y1 = Math.min(a.y(), b.y()), y2 = Math.max(a.y(), b.y());
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    grid[x][y] = true;
                }
            }
        }

        public void execute(Point a, Point b, int[][] grid) {
            int x1 = Math.min(a.x(), b.x()), x2 = Math.max(a.x(), b.x());
            int y1 = Math.min(a.y(), b.y()), y2 = Math.max(a.y(), b.y());
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    grid[x][y] = grid[x][y] + 1;
                }
            }
        }
    },
    OFF("turn off") {
        public void execute(Point a, Point b, boolean[][] grid) {
            int x1 = Math.min(a.x(), b.x()), x2 = Math.max(a.x(), b.x());
            int y1 = Math.min(a.y(), b.y()), y2 = Math.max(a.y(), b.y());
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    grid[x][y] = false;
                }
            }
        }

        public void execute(Point a, Point b, int[][] grid) {
            int x1 = Math.min(a.x(), b.x()), x2 = Math.max(a.x(), b.x());
            int y1 = Math.min(a.y(), b.y()), y2 = Math.max(a.y(), b.y());
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    grid[x][y] = Math.max(grid[x][y] - 1, 0);
                }
            }
        }
    },
    TOGGLE("toggle") {
        public void execute(Point a, Point b, boolean[][] grid) {
            int x1 = Math.min(a.x(), b.x()), x2 = Math.max(a.x(), b.x());
            int y1 = Math.min(a.y(), b.y()), y2 = Math.max(a.y(), b.y());
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    grid[x][y] = !grid[x][y];
                }
            }
        }

        public void execute(Point a, Point b, int[][] grid) {
            int x1 = Math.min(a.x(), b.x()), x2 = Math.max(a.x(), b.x());
            int y1 = Math.min(a.y(), b.y()), y2 = Math.max(a.y(), b.y());
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    grid[x][y] = grid[x][y] + 2;
                }
            }
        }
    };

    @Getter
    private String text;

    public abstract void execute(Point a, Point b, boolean[][] grid);
    public abstract void execute(Point a, Point b, int[][] grid);

    Command(String txt) {
        text = txt;
    }
}
