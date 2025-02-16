package theletterleaguer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DictionaryApiExample {
    public static void main(String[] args) throws Exception {
        // Replace <word> with the actual word you want to look up
        String word = "zhuzh";
        String apiUrl = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;

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
                System.out.println(response.toString());
            } finally {
                connection.disconnect();
            }
        } catch (FileNotFoundException e) {
            // Handle the exception
            System.out.println("the set of letters put in likely don't make a word, try again");
        } catch (Exception e) {
            // Handle other exceptions
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}