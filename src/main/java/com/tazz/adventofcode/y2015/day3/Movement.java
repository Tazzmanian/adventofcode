package com.tazz.adventofcode.y2015.day3;

import lombok.Getter;

public enum Movement {

    NORTH('^') {
        @Override
        public Position updatedPosition(Position currentPosition) {
            return new Position(currentPosition.x() + 1, currentPosition.y());
        }
    },
    SOUTH('v') {
        @Override
        public Position updatedPosition(Position currentPosition) {
            return new Position(currentPosition.x() - 1, currentPosition.y());
        }
    },
    WEST('<') {
        @Override
        public Position updatedPosition(Position currentPosition) {
            return new Position(currentPosition.x(), currentPosition.y() - 1);
        }
    },
    EAST('>') {
        @Override
        public Position updatedPosition(Position currentPosition) {
            return new Position(currentPosition.x(), currentPosition.y() + 1);
        }
    };

    @Getter
    private char move;

    Movement(char move) {
        this.move = move;
    }

    public Position updatedPosition(Position currentPosition) {
        return currentPosition;
    }

    public static Movement fromChar(char c) {
        switch (c) {
            case '^': return NORTH;
            case 'v': return SOUTH;
            case '<': return WEST;
            case '>': return EAST;
            default:
                throw new IllegalArgumentException("Unknown move: " + c);
        }
    }

}
