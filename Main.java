package bullscows;



public class Main {
    public static void main(String[] args) {
        Secret game = new Secret();
        int counter = 0;
        int outcome = 0;

        game.settings();

        do {
            counter++;
            System.out.printf("Turn %d: \n", counter);
            outcome = game.makeGuess();
        } while (outcome == 0);

        if (outcome == 1) {
            System.out.println("Congratulations! You guessed the secret code!");
        } else {
            System.out.println("Ok, you gave up, here is the code: " + game.getPassword());
        }



    }
}

