package bullscows;

import java.util.*;

public class Secret {
    private String password;


    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    private int cows(String guess) {

        int counter = 0;

        for (int i = 0; i < guess.length(); i++) {
            counter += getPassword().contains(String.valueOf(guess.charAt(i))) ? 1 : 0;
        }

        return counter - bulls(guess);
    }

    private int bulls(String guess) {

        int counter = 0;

        for (int i = 0; i < guess.length(); i++) {
            counter += guess.charAt(i) == getPassword().charAt(i) ? 1 : 0;
        }
        return counter;
    }

    public int makeGuess() {
        final String text = "Grade: %d bull(s) and %d cow(s).";
        Scanner scanner = new Scanner(System.in);
        String guess = scanner.nextLine();
        if ("exit".equals(guess)) {
            return -1;
        }

        System.out.printf(text + "\n", bulls(guess), cows(guess));

        return bulls(guess) == getPassword().length() ? 1 : 0;
    }

    public void settings() {
        final String error = "Error: can't generate a secret number with a length of more than 36" +
                " because there aren't enough unique characters.";
        Scanner scanner = new Scanner(System.in);
        int length = 0;
        int numChar = 0;
        String input;
        while(true) {
            System.out.println("Input the length of the secret code");
            input = scanner.nextLine();
            try {
                length = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.printf("Error, \"%s\" ist not a valid length!\n", input);
                continue;
            }


            if (length > 36) {
                System.out.println(error);
            } else {
                break;
            }
        }
        while(true) {
            System.out.println("Input the number of possible characters in the code:");
            input = scanner.nextLine();
            try {
                numChar = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.printf("Error, \"%s\" is not a valid amount of Characters!\n", input);
                continue;
            }


            if (numChar > 36 || numChar < length || numChar < 10) {
                System.out.printf("Error: minimum of %d and maximum of 36 allowed! \n", Math.max(length, 10));
            } else {
                 break;
            }
        }

        setPassword(createSecretString(length, numChar));
        String range = numChar < 11 ? "(0 - 9)" :
                numChar == 11 ? "(0 - 9, a)" : "(0 - 9, a - " + (char) ('a' + numChar - 11) + ")";

        System.out.println("The secret is prepared: " + "*".repeat(length) + " " + range);
    }

    private String createSecretString(int length, int numChar) {
        final String allChars = "0123456789abcdefghijklmnopqrstuvwxyz";
        //numChar says, how many characters are allowed. thats how many get saved in "allowed".
        StringBuilder allowed = new StringBuilder(allChars.substring(0, numChar));
        StringBuilder secretString = new StringBuilder(length);
        Random rand = new Random();

        int temp;

        //Here we build our secretstring one by one, randomly selecting from the allowed chars (and making sure no doubles)
        for (int i = numChar; i > numChar - length; i--) {
            temp = rand.nextInt(i);
            secretString.append(allowed.charAt(temp));
            allowed.deleteCharAt(temp);
        }

        return secretString.toString();
    }

}
