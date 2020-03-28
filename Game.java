import java.util.*;
import java.io.*;
import java.util.regex.*;  

class Game {
    private String title;
    private char[] convertMovieTitle;
    private int wrongGuesses;
    private List<Character> charactersUsed;
    private List<Character> wrongCharactersUsed;
    private boolean hasWon = false;

    public Game(){
        this.title = getRandomTitle();
        this.convertMovieTitle = convertMovieTitle(this.title);
        this.wrongGuesses = 0;
        charactersUsed = new ArrayList<>();
        wrongCharactersUsed = new ArrayList<>();
    }

    public char[] getConvertedTitle(){
        return this.convertMovieTitle;
    }

    public int getWrongGuesses(){
        return this.wrongGuesses;
    }

    public List<Character> getCharactersUsed(){
        return this.charactersUsed;
    }

    public List<Character> getWrongCharactersUsed(){
        return this.wrongCharactersUsed;
    }

    public boolean getHasWon(){
        return hasWon;
    }

    public String getTitle(){
        return this.title;
    }

    public void searchInTitle(char userInput){ // search method to see if input is in title
        // regex used to check if user input is only single valid character

        String a = Character.toString(userInput);
        Pattern p = Pattern.compile("^[a-z]{1}$");
        Matcher m = p.matcher(a); 
        boolean result = m.matches();

        if(result){
            if(this.charactersUsed.contains(userInput)){ // prints error message if character already entered and no points deducted
                System.out.println("Character has already been entered, please enter another.");
            } else if(this.title.indexOf(userInput) != -1){ // checks if input is in title
                for(int i = 1; i < this.title.length(); i++){ // loops through input and replaces each char in converted title to valid matching user input
                    if(this.title.charAt(0) == userInput){ // only changes the first char to upper case
                        this.convertMovieTitle[0] = Character.toUpperCase(userInput);
                    } 
                    if(this.title.charAt(i) == userInput){
                        this.convertMovieTitle[i] = userInput;
                    }
                }
    
                this.charactersUsed.add(userInput); // add to list of character used
                System.out.println(this.convertMovieTitle);
    
                if(this.title.equals(String.valueOf(this.convertMovieTitle).toLowerCase())){ // checks to see if all letters are guessed
                    hasWon = true;
                }
    
            } else { // if wrong guess increase and add letter guessed to list
                this.wrongGuesses++;
                this.wrongCharactersUsed.add(userInput);
            }
        } else {
            System.out.println("Invalid input, please try again.");
        }
    }

    private String getRandomTitle() { // generates a random movie title
        List<String> listOfMovies = new ArrayList<>();

        try {
            File file = new File("movies.txt");
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                listOfMovies.add(scanner.nextLine().replaceAll("[^a-zA-Z]+", ""));
            }

            scanner.close();
        } catch(Exception e){
            System.out.println("Could not generate a movie.");
        }

        int randomNumber = (int) (Math.random() * listOfMovies.size());

        return listOfMovies.get(randomNumber).trim();
    }

    private char[] convertMovieTitle(String t){ // converts the random title generated to the correct format
        char[] c = t.replaceAll("[A-Za-z0-9]", "*").toCharArray();  // replaces all valid characters to "*"
        return c;
    }
}