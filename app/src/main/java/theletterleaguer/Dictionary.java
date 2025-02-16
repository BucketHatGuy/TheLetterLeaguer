package theletterleaguer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Dictionary
{
    private static Set<String> wordsSet;

    public static void dictionarySetup() throws IOException
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

    public static String[] findDefinition(String word) throws Exception {
        // Replace <word> with the actual word you want to look up
        String apiUrl = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;
        Integer defBegin = 0;
        Integer defEnd = 0;
        String definition = "";

        try{
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                defBegin = (response.indexOf("definition\":")) + 12;
                defEnd = response.indexOf(",\"synonyms");
                definition = response.substring(defBegin, defEnd);

                String[] listToReturn = new String[]{word, definition};
                return listToReturn;
            } finally {
                connection.disconnect();
            }
        } catch (FileNotFoundException e) {
            // Handle the exception
            System.out.println("that probably isn't a word lol, try again");
            return null;
        } catch (Exception e) {
            // Handle other exceptions
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }
}