package theletterleaguer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);
    static String scannerInput = "";
    static HashSet<String> setWithoutDuplicates;
    static List<String> listWithoutDuplicates;

        public static void userInterface(){
            while(!scannerInput.equals("stop")){
                Validate.validWords.clear();
                System.out.println("what would you like to do? (use \"help\" if you want the options)");
                scannerInput = scanner.nextLine();

                String[] inputSplitter = scannerInput.split("\\s+");

                switch(inputSplitter[0])
                {
                case "scramble":
                    if((inputSplitter.length) == 2){
                        String letters = Validate.input(inputSplitter[1]);

                        if(letters != null){
                            Validate.letterCombos(letters, "");
                            setWithoutDuplicates = new HashSet<>(Validate.validWords);
                            listWithoutDuplicates = new ArrayList<>(setWithoutDuplicates);

                            if(listWithoutDuplicates.size() <= 50){
                                System.out.println("These are your words: " + listWithoutDuplicates);
                            } else {
                                System.out.println("We did it, but there's a fuck ton of words lol");
                            }
                            Validate.validWords.clear();
                        } else {
                            System.out.println("your letters don't meet the criteria, please try again");
                        }
                    } else {
                        System.out.println
                        ("^ this command requires the command's name and the letters in one, with a space to seperate them");
                    }
                break;
                
                case "define":
                    if((inputSplitter.length) == 2){
                        try {
                            Dictionary.findDefinition(inputSplitter[1]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println
                        ("^ this command requires the command's name and the letters in one, with a space to seperate them");
                    }
                break;
                
                case "stop":
                    scanner.close();
                break;
                
                case "help":
                    System.out.println("You can use the following commands:");
                    System.out.println("\"scramble (letters here)\" = find words out of your letters");
                    System.out.println("\"define (word here)\" = find the definition of a word");
                    System.out.println("\"stop\" = stop the program \n");
                break;
                
                default:
                    System.out.println("Invalid input. Please try again. \n");
                break;
                }
            }
        }

        public static void main(String[] args) throws Exception{
        //starts by initiating the dictionary into one list. without this, there wouldn't be a dictionary to reference.
        try {
            Dictionary.dictionarySetup();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JDA jda = (JDA) JDABuilder.createDefault("")
            .setActivity(Activity.watching("from inside your walls"))
            .addEventListeners(new Commands())
            .build();

        jda.upsertCommand("fart","farted").queue();

        userInterface();
    }
}