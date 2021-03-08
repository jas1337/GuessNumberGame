package efs.task.syntax;

import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class GuessNumberGame {

    private int upperBound;

    private int secretNumber;

    private int availableChances;

    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) throws IllegalArgumentException {
        Random rand = new Random();

        try {
            upperBound = Integer.parseInt(argument);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(UsefulConstants.WRONG_ARGUMENT);
        }

        secretNumber = rand.nextInt(upperBound);
        availableChances = (int)(Math.log(upperBound) / Math.log(2));
    }

    public void play() {
        int value;
        Scanner input = new Scanner(System.in);

        System.out.printf(UsefulConstants.VALUES_RANGE, Integer.toString(upperBound));

        while (availableChances > 0) {
            System.out.printf(UsefulConstants.GIVE_ME, Integer.toString(availableChances));
            try {
                value = input.nextInt();

                if (validate(value)) {
                    checkResult(value);
                    availableChances--;
                } else {
                    System.out.printf(UsefulConstants.WRONG_RANGE, Integer.toString(upperBound));
                }
            } catch (InputMismatchException e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                input.next();
            }
        }
        System.out.println(UsefulConstants.UNFORTUNATELY);
    }

    private boolean validate(Integer value) {
        return value > 0 && value <= UsefulConstants.MAX_UPPER_BOUND;
    }

    private void checkResult(Integer value) {
        if (value < secretNumber) {
            System.out.println(UsefulConstants.TOO_LESS);
        } else if (value == secretNumber) {
            System.out.println(UsefulConstants.CONGRATULATIONS);
            System.exit(0);
        } else if (value > secretNumber) {
            System.out.println(UsefulConstants.TOO_MUCH);
        }
    }
}
