package theletterleaguer;

import java.util.ArrayList;
import java.util.Scanner;

public class Validate {
    static ArrayList<String> validWords = new ArrayList<String>();
    static Scanner userInterface = new Scanner(System.in);
    static String input = "";

    static void letterCombos(String str, String answer){
        // if string is empty
        if (str.length() == 0) {
            // if the word is valid
            if(word(answer)){
                // add the word into the arraylist
                validWords.add(answer);
            }
        }
 
        //takes all seven letters and tries to find anagrams
        for (int i = 0; i < str.length(); i++) {
            //finds the character in str at i
            char character = str.charAt(i);
 
            // rest of the string after excluding the ith character
            String remainder = str.substring(0, i) + str.substring(i + 1);

            // recursive loop to go back to this method
            letterCombos(remainder, answer + character);
        }

        //breaks down the seven letters to find the rest of the words. once it's less than or equal to 2, it stops.
        if(!(str.length() <= 2)){
            for (int i = 0; i < str.length(); i++) {
                // rest of the string after excluding the ith character
                String remainder = str.substring(0, i) + str.substring(i + 1);

                // recursive loop to go back to this method
                letterCombos(remainder, "");
            }
        }
    }
    
    static boolean word(String word){
        // look in dictionary.java
        if(Dictionary.contains(word)){
            return true;
        } else {
            return false;
        }
    }

    public static String input(){
        boolean eightOrLessCheck = false;
        boolean onlyLettersCheck = false;
        String letters = "";

        while(!(eightOrLessCheck && onlyLettersCheck)){
            // asks for the user's letters
            System.out.println("what letters do you have? (Include up to eight if you're trying to build off of a word): ");
            letters = userInterface.nextLine();
            
            // checks to see if the user's letters are equal to eight or less
            eightOrLessCheck = letters.length() <= 8;

            // checks to see if the user's letters are all letters by using regex
            onlyLettersCheck = letters.matches("[a-zA-Z]+");

            // tells the user to not break this if they entered too many letters or non-letters
            if(!(eightOrLessCheck && onlyLettersCheck)){
                System.out.println("please don't try and break this");
            }
        }
        return letters;
    }
}
