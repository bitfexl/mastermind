package com.github.bitfexl.mastermind;

import java.util.Iterator;

/**
 * Simply generates all possible rows.
 */
public class RowGenerator implements GuessGenerator {
    @Override
    public Iterator<GuessRow> iterator() {
        return new Iterator<>() {
            final Color[] values = Color.values();

            int index = -1;

            @Override
            public boolean hasNext() {
                return index < 4096 - 1; // 4 cols
            }

            @Override
            public GuessRow next() {
                index++;

                return new GuessRow(
                        values[get(0)],
                        values[get(1)],
                        values[get(2)],
                        values[get(3)]
                );
            }

            private int get(int col) {
                return (index / (int)Math.pow(values.length, col)) % values.length;
            }
        };
    }
}
