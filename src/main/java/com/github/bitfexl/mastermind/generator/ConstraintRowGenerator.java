package com.github.bitfexl.mastermind.generator;

import com.github.bitfexl.mastermind.ConstraintRow;
import com.github.bitfexl.mastermind.GuessRow;

import java.util.Iterator;

/**
 * Filters the generated row to match the constraints.
 */
public class ConstraintRowGenerator implements GuessGenerator {
    private final ConstraintRow constraintRow;

    private final GuessGenerator base;

    public static ConstraintRowGenerator forRows(ConstraintRow row, ConstraintRow... constraintRows) {
        ConstraintRowGenerator generator = new ConstraintRowGenerator(row);

        for (ConstraintRow newRow : constraintRows) {
            generator = new ConstraintRowGenerator(newRow, generator);
        }

        return generator;
    }

    public ConstraintRowGenerator(ConstraintRow constraintRow) {
        this.constraintRow = constraintRow;
        this.base = new RowGenerator();
    }

    public ConstraintRowGenerator(ConstraintRow constraintRow, GuessGenerator base) {
        this.constraintRow = constraintRow;
        this.base = base;
    }

    @Override
    public Iterator<GuessRow> iterator() {
        return new Iterator<>() {
            private final Iterator<GuessRow> rowIterator = base.iterator();

            private GuessRow nextRow = findNext();

            @Override
            public boolean hasNext() {
                return nextRow != null;
            }

            @Override
            public GuessRow next() {
                try {
                    return nextRow;
                } finally {
                    nextRow = findNext();
                }
            }

            private GuessRow findNext() {
                while (rowIterator.hasNext()) {
                    GuessRow row = rowIterator.next();

                    if (constraintRow.check(row)) {
                        return row;
                    }
                }

                return null;
            }
        };
    }
}
