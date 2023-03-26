package com.github.bitfexl.mastermind;

public enum Color {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    ORANGE,
    WHITE,
    SILVER,
    PINK;

    public static Color fromShortCode(String code) {
        for (Color color : values()) {
            if (color.toString().startsWith(code.toUpperCase())) {
                return color;
            }
        }

        throw new IllegalArgumentException("Code '" + code + "' unknown.");
    }
}