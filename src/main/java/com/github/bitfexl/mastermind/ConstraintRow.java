package com.github.bitfexl.mastermind;

public class ConstraintRow {
    public final GuessRow guessRow;

    private final Hints constraints;

    public ConstraintRow(GuessRow guessRow, Hints constraints) {
        this.guessRow = guessRow;
        this.constraints = constraints;
    }

    public ConstraintRow(GuessRow guessRow, int correctColorAndPosition, int correctColor) {
        this.guessRow = guessRow;
        this.constraints = new Hints(correctColorAndPosition, correctColor);
    }

    public boolean check(GuessRow guessRow) {
        Hints guessHints = this.guessRow.guess(guessRow);
        return guessHints.equals(constraints);
    }

    public Hints getConstraints() {
        return constraints;
    }
}
