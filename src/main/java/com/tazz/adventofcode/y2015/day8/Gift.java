package com.tazz.adventofcode.y2015.day8;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public record Gift(String literal) {

    public int size() {
        return literal.length() - escapeCharsCount();
    }

    private int escapeCharsCount() {
        int count = 0;
        for (int i = 1; i < literal.length() - 1; i++) {
            var c = literal.charAt(i);
            if (c >= 'A' && c <= 'Z' ||
                    c >= 'a' && c <= 'z') {
                count++;
            } else if (c == '\\') {
                if (literal.charAt(i + 1) == 'x') {
                    i += 3;
                } else {
                    i++;
                }
                count++;
            }

        }

        return count;
    }

    public int encodedSize() {
        return encodedCharsCount() - literal.length();
    }

    private int encodedCharsCount() {
        int count = 6;
        for (int i = 1; i < literal.length() - 1; i++) {
            var c = literal.charAt(i);
            if (c >= 'A' && c <= 'Z' ||
                    c >= 'a' && c <= 'z' ||
                    c >= '0' && c <= '9') {
                count++;
            } else if (c == '\\' || c == '"') {
                count += 2;
            }
        }

        return count;
    }

}
