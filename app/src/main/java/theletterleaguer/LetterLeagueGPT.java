package theletterleaguer;

import java.util.Scanner;

public class LetterLeagueGPT {
    // returns all of the string combinations
    static void stringMixer(String str, String answer)
    {
        // if string is empty
        if (str.length() == 0) {
            System.out.print(answer + " ");
            return;
        }
 
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
 
            // rest of the string after excluding the ith character
            String remainder = str.substring(0, i) + str.substring(i + 1);

            // recursive loop to go back to this method
            stringMixer(remainder, answer + character);
        }
    }

    public static String newLetterCombinationValidator(){
        boolean eightOrLessCheck = false;
        boolean onlyLettersCheck = false;
        String letters = "";
        Scanner userInput = new Scanner(System.in);

        while((eightOrLessCheck && onlyLettersCheck) != true){
            // asks for the user's letters
            System.out.println("what letters do you have? (Include up to eight if you're trying to build off of a word): ");
            letters = userInput.nextLine();
            
            // checks to see if the user's letters are equal to eight or less
            eightOrLessCheck = letters.length() <= 8;

            // checks to see if the user's letters are all letters by using regex
            onlyLettersCheck = letters.matches("[a-zA-Z]+");

            // tells the user to not break this if they entered too many letters or non-letters
            if((eightOrLessCheck && onlyLettersCheck) != true){
                System.out.println("please don't try and break this");
            }
        }

        return letters;
    }

    public static void main(String[] args){
        String letters = newLetterCombinationValidator();
        String[] wordsFound;

        stringMixer(letters, "");
    }
}