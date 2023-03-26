package com.github.bitfexl.mastermind;

import com.github.bitfexl.mastermind.generator.ConstraintRowGenerator;
import com.github.bitfexl.mastermind.generator.GuessGenerator;
import com.github.bitfexl.mastermind.generator.RowGenerator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Scanner stdin = new Scanner(System.in);

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {
        GuessGenerator generator = new RowGenerator();
        GuessEvaluator evaluator = new GuessEvaluator(generator);

        while (true) {
            System.out.print("Enter a row and constraints (#### R C): ");
            ConstraintRow constraintRow = readFullRow();

            generator = new ConstraintRowGenerator(constraintRow, generator);
            evaluator.setGenerator(generator);

            Map<Integer, Set<GuessRow>> evaluatedRows = evaluator.evaluate();
            int min = Arrays.stream(evaluatedRows.keySet().toArray(new Integer[0])).mapToInt(i -> i).min().orElseThrow();

            System.out.println();
            for (GuessRow guess : evaluatedRows.get(min)) {
                System.out.println("" + guess.get(0).toString().charAt(0) + guess.get(1).toString().charAt(0) + guess.get(2).toString().charAt(0) + guess.get(3).toString().charAt(0));
            }
            System.out.println("\nAvg rows left: " + (min / 6.0)); // todo: somethings not quite right
            System.out.println();
        }
    }

    public static GuessRow readRow() {
        return new GuessRow(Arrays.stream(stdin.nextLine().split("")).map(Color::fromShortCode).toList());
    }

    public static ConstraintRow readFullRow() {
        String[] parts = stdin.nextLine().split("\\s");
        return new ConstraintRow(
                new GuessRow(Arrays.stream(parts[0].split("")).map(Color::fromShortCode).toList()),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2])
        );
    }
}