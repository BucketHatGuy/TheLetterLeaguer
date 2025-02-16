package theletterleaguer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    static Scanner userInterface = new Scanner(System.in);
    static String input = "";
    static HashSet<String> setWithoutDuplicates;
    static List<String> listWithoutDuplicates;

        public static void main(String[] args) throws Exception{
        //starts by initiating the dictionary into one list. without this, there wouldn't be a dictionary to reference.
        try {
            Dictionary.dictionarySetup();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(!input.equals("stop")){
            Validate.validWords.clear();
            System.out.println("what would you like to do? (use \"help\" if you want the options)");
            input = userInterface.nextLine();

            if(input.contains("find words")){
                String letters = Validate.input();
                Validate.letterCombos(letters, "");
                setWithoutDuplicates = new HashSet<>(Validate.validWords);
                listWithoutDuplicates = new ArrayList<>(setWithoutDuplicates);

                System.out.println("These are your words: " + listWithoutDuplicates);
            } 
            
            else if(input.contains("find def")){
                System.out.println("what word?");
                input = userInterface.nextLine();

                Dictionary.findDefinition(input);
            } 
            
            else if(input.contains("stop")){
                continue;
            } 
            
            else if(input.contains("help")){
                System.out.println("You can use the following commands: ");
                System.out.println("\"find words\" = find words out of your letters");
                System.out.println("\"find def\" = find the definition of a word");
                System.out.println("\"stop\" = stop the program");
            } 
            
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        userInterface.close();
    }
}