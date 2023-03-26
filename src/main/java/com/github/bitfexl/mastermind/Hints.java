package com.github.bitfexl.mastermind;

/**
 * Hints for a guess.
 *
 * @param correct   Pins that are in the correct position and color.
 * @param onlyColor Pins that are the correct color but not in the correct position.
 */
public record Hints(int correct, int onlyColor) {
}
