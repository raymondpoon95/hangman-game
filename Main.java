import java.util.*;

public class Main {
    public static void main(String args[]){
        Game game = new Game();

        String movie = new String(game.getConvertedTitle());

        Scanner scanner = new Scanner(System.in);

        System.out.println("You have 10 chances to guess the title of the movie.");
        System.out.println("You are guessing for the movie: " + movie);

        while(game.getWrongGuesses() <= 10){

            if(game.getHasWon()){
                System.out.println("You have correctly guessed the movie: " + game.getTitle() + ", within 10 guesses.");
                break;
            }

            System.out.println("You have guessed incorrectly (" + game.getWrongGuesses() + ") times, wrong letters: " + game.getWrongCharactersUsed());

            char userInput = scanner.next().charAt(0);

            // String a = Character.toString(userInput);
            // Pattern p = Pattern.compile("^[a-z]{1}$");
            // Matcher m = p.matcher(a); 
            // boolean result = m.matches();

            // if(result && a.length() > 0 && a.length() <= 1){
            //     System.out.println("hello");
            //     game.searchInTitle(userInput);
            // } else {
            //     System.out.println("Invalid input, please try again.");
            // }

            game.searchInTitle(userInput);
        }

        if(!game.getHasWon()){
            System.out.println("Sorry you have lossed the game. You could no guess the movie within 10 chances");
        }

        scanner.close();
    }
}