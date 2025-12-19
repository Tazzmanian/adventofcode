package com.tazz.adventofcode.y2015.day2;

public record Box(int side1, int side2, int side3) {

    private int slack() {
        return Math.min(
                Math.min(side1 * side2, side1 * side3), side2 * side3);
    }

    public int area() {
        return 2 * (side1 * side2 + side1 * side3 + side2 * side3) + slack();
    }

    public int ribbon() {
        var minSides = Math.min(
                Math.min(side1 + side2, side1 + side3), side2 + side3);

        return 2 * minSides + side1 * side2 * side3;
    }
}
