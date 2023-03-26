package com.github.bitfexl.mastermind;

public class ConstraintRow {
    public final GuessRow guessRow;

    private final GuessRow.Hints constraints;

    public ConstraintRow(GuessRow guessRow, GuessRow.Hints constraints) {
        this.guessRow = guessRow;
        this.constraints = constraints;
    }

    public ConstraintRow(GuessRow guessRow, int correctColorAndPosition, int correctColor) {
        this.guessRow = guessRow;
        this.constraints = new GuessRow.Hints(correctColorAndPosition, correctColor);
    }

    public boolean check(GuessRow guessRow) {
        GuessRow.Hints guessHints = this.guessRow.guess(guessRow);
        return guessHints.equals(constraints);
    }

    public GuessRow.Hints getConstraints() {
        return constraints;
    }
}
