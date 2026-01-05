package com.tazz.adventofcode.y2015.day11;

import java.util.List;

public class SantasPasswordGenerator {

    private static final List<Character> restrictions = List.of('i', 'o', 'l');
    private char startLetter = 'a';
    private char endLetter = 'z';

    public String update(String password) {
        var passArray = password.toCharArray();

        do {
            int over = 0;
            for (int i = 7; i >= 0; i--) {
                var current = passArray[i] + over;
                over = 0;
                passArray[i] = (char) (passArray[i] + 1);
                if (passArray[i] > endLetter) {
                    passArray[i] = startLetter;
                    over++;
                }
                if (over == 0) {
                    break;
                }
            }
        } while  (checkAndUpdate(passArray) ||
                !containsIncreasingStraight(passArray) ||
                !containsDuplicates(passArray));

        return String.valueOf(passArray);
    }

    public boolean containsIncreasingStraight(char[] pass) {
        for (int i = 2; i < pass.length; i++) {
            if (pass[i - 2] + 1 == pass[i - 1] && pass[i - 2] + 2 == pass[i]) {
                return true;
            }
        }

        return false;
    }

    public boolean checkAndUpdate(char[] pass) {
        boolean flag = false;
        for (int i = 0; i < pass.length; i++) {
            if (!flag && restrictions.contains(pass[i])) {
                flag = true;
                pass[i] = (char) (pass[i] + 1);
                continue;
            }
            if (flag) {
                pass[i] = startLetter;
            }
        }
        flag = false;
        return flag;
    }

    public boolean containsDuplicates(char[] pass) {
        int count = 0;
        for (int i = 1; i < pass.length; i++) {
            if (pass[i-1] == pass[i]) {
                count++;
                i++;
            }
        }

        return count >= 2;
    }


}
