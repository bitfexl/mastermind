package com.github.bitfexl.mastermind;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner stdin = new Scanner(System.in);

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {
        GuessGenerator generator = new RowGenerator();

        while (true) {
            System.out.print("Enter a row and constraints (#### R C): ");
            ConstraintRow constraintRow = readFullRow();

            generator = new ConstraintRowGenerator(constraintRow, generator);

            System.out.println();
            for (GuessRow guess : generator) {
                System.out.println("" + guess.get(0).toString().charAt(0) + guess.get(1).toString().charAt(0) + guess.get(2).toString().charAt(0) + guess.get(3).toString().charAt(0));
            }
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