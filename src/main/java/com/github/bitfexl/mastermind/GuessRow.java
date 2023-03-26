package com.github.bitfexl.mastermind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuessRow {
    private final List<Color> colors;

    public GuessRow(List<Color> colors) {
        this.colors = colors;
    }

    public GuessRow(Color... colors) {
        this.colors = List.of(colors);
    }

    /**
     * Make a guess (teat the current row as the right one).
     * @param guessRow The guessed row.
     * @return The hints for the given guess.
     */
    public Hints guess(GuessRow guessRow) {
        if (this.size() != guessRow.size()) {
            throw new IllegalArgumentException("Can only compare rows with equal length.");
        }
        int correct = 0, onlyColor = 0;

        final Map<Color, Integer> thisRestCount = new HashMap<>();
        final Map<Color, Integer> guessRestCount = new HashMap<>();

        for (int i = 0; i < this.size(); i++) {
            final Color guess = guessRow.get(i);
            final Color actual = this.get(i);

            if (actual == guess) {
                correct++;
            } else {
                thisRestCount.put(actual, thisRestCount.getOrDefault(actual, 1));
                guessRestCount.put(guess, guessRestCount.getOrDefault(guess, 1));
            }
        }

        for (Color contained : thisRestCount.keySet()) {
            onlyColor += Math.min(thisRestCount.get(contained), guessRestCount.getOrDefault(contained, 0));
        }

        return new Hints(correct, onlyColor);
    }

    public boolean contains(Color c) {
        return colors.contains(c);
    }

    public int count(Color c) {
        int count = 0;

        for (Color tc : colors) {
            if (tc == c) {
                count++;
            }
        }

        return count;
    }

    public Color get(int i) {
        return colors.get(i);
    }

    public int size() {
        return colors.size();
    }
}
