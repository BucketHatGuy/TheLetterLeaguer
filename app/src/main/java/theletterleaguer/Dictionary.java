package theletterleaguer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Dictionary
{
    private static Set<String> wordsSet;

    public static void dictionary() throws IOException
    {
        // gets the path to the file "words.txt"
        Path path = Paths.get("words.txt");

        //reads the whole file and makes it a byte array
        byte[] readBytes = Files.readAllBytes(path);

        //converts byte array into string
        String wordListContents = new String(readBytes, "UTF-8");

        //splits the big string into a string array, specifically using the "\n" seen in the words.txt to seperate all of the words
        String[] words = wordListContents.split("\n");

        //makes a set to store all of these words
        wordsSet = new HashSet<>();

        //adds all of the words to the set
        Collections.addAll(wordsSet, words);
    }

    public static boolean contains(String word)
    {
        //returns a boolean depending on if the word given is contained in the set using the contains() method. 
        return wordsSet.contains(word);
    }
}