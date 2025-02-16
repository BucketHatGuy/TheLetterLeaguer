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
            if(Dictionary.contains(answer)){
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

    public static String input(String letters){
        boolean eightOrLessCheck = false;
        boolean onlyLettersCheck = false;
            
        // checks to see if the user's letters are equal to eight or less
        eightOrLessCheck = letters.length() <= 8;

        // checks to see if the user's letters are all letters by using regex
        onlyLettersCheck = letters.matches("[a-zA-Z]+");

        // tells the user to not break this if they entered too many letters or non-letters
        if(!(eightOrLessCheck && onlyLettersCheck)){
            System.out.println("please don't try and break this");
            return null;
        } else {
            return letters.toLowerCase();
        }
    }
}

