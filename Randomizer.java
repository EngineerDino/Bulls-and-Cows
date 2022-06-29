package bullscows;

public class Randomizer {


    public static long rand(int length) {

        String seed;
        StringBuilder secretNumber = new StringBuilder();
        char temp;

        while(secretNumber.length() != length) {

            //Get a random double and start only after the comma.
            seed = Double.toString(Math.random()).substring(2);
            //Reset our secretNumber.
            secretNumber.delete(0, secretNumber.length());
            //Make sure there is no 0 at the beginning, so just cut off leading zeros.
            while (seed.charAt(0) == '0') {
                seed = seed.substring(1);
            }
            // copy char one by one to secretNumber and delete all further occurencies of that digit.

            while(seed.length() != 0 && secretNumber.length() != length) {
                secretNumber.append(seed.charAt(0));
                seed = seed.replace(String.valueOf(seed.charAt(0)), "");
            }
        }

        return Long.parseLong(secretNumber.toString());
    }
}


/* ++++++++ Just an experiment to write my own Randomnumbergenerator via the System.nanoTime();

/* public static int rand(int length) {

        String seed;
        StringBuilder secretNumber = new StringBuilder();
        char temp;

        while(secretNumber.length() != length) {

            //Get a new nanoTime number, reverse it and reset the stringbuilder.
            seed = new StringBuilder(Long.toString(System.nanoTime())).reverse().toString();
            secretNumber.delete(0, secretNumber.length());
            //Make sure there is no 0 at the beginning, so just cut off leading zeros.
            while (seed.charAt(0) == '0') {
                seed = seed.substring(1);
            }
            // copy char one by one to secretNumber and delete all further occurencies of that digit.

            while(seed.length() != 0 && secretNumber.length() != length) {
                secretNumber.append(seed.charAt(0));
                seed = seed.replace(String.valueOf(seed.charAt(0)), "");
            }
        }

        return Integer.parseInt(secretNumber.toString());
    } */
