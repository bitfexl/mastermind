package com.github.bitfexl.mastermind;

import com.github.bitfexl.mastermind.generator.ConstraintRowGenerator;
import com.github.bitfexl.mastermind.generator.GuessGenerator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GuessEvaluator {
    private GuessGenerator generator;

    private final Set<Hints> hints = Set.of(
            new Hints(0, 0),
            new Hints(1, 0),
            new Hints(1, 1),
            new Hints(0, 1),
            new Hints(2, 0),
            new Hints(0, 2)
    );

    public GuessEvaluator(GuessGenerator generator) {
        this.generator = generator;
    }

    /**
     * Evaluate all guesses returned by the guess generator.
     * For efficiency, it does not evaluate all possible answer hints.
     * Can take quite long (?).
     * @return A map with all leftover rows (all hints are added up) and all the guesses that lead to this number of rows.
     */
    public Map<Integer, Set<GuessRow>> evaluate() {
        final Map<Integer, Set<GuessRow>> evaluatedGuesses = new HashMap<>();

        for (GuessRow row : generator) {
            int count = 0;

            for (Hints hint : hints) {
                for (GuessRow r : new ConstraintRowGenerator(new ConstraintRow(row, hint))) {
                    count++;
                }
            }

            if (!evaluatedGuesses.containsKey(count)) {
                evaluatedGuesses.put(count, new HashSet<>());
            }
            evaluatedGuesses.get(count).add(row);
        }

        return evaluatedGuesses;
    }

    public void setGenerator(GuessGenerator generator) {
        this.generator = generator;
    }
}
