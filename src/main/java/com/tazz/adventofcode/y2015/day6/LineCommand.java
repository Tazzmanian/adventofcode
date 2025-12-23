package com.tazz.adventofcode.y2015.day6;

record LineCommand(Command command, Point a, Point b) {

    public void execute(boolean[][] grid) {
        command.execute(a, b, grid);
    }

    public void execute(int[][] grid) {
        command.execute(a, b, grid);
    }
}
