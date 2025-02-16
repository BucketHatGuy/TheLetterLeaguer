package theletterleaguer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class Commands extends ListenerAdapter{
    static HashSet<String> setWithoutDuplicates;
    static List<String> listWithoutDuplicates;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        if (event.getName().equals("scramble")){
            event.deferReply().queue();

            OptionMapping lettersReal = event.getOption("letters");

            if(lettersReal != null){
                String letters = lettersReal.getAsString();
                letters = letters.toLowerCase();

            boolean eightOrLessCheck = false;
            boolean onlyLettersCheck = false;
            
            // checks to see if the user's letters are equal to eight or less
            eightOrLessCheck = letters.length() <= 8;

            // checks to see if the user's letters are all letters by using regex
            onlyLettersCheck = letters.matches("[a-zA-Z]+");

            // tells the user to not break this if they entered too many letters or non-letters
            if(!(eightOrLessCheck && onlyLettersCheck)){
                event.getHook().sendMessage("your letters don't meet the criteria, please try again").setEphemeral(true).queue();
                return;
            }
                Validate.letterCombos(letters, "");
                setWithoutDuplicates = new HashSet<>(Validate.validWords);
                listWithoutDuplicates = new ArrayList<>(setWithoutDuplicates);

                if(listWithoutDuplicates.size() <= 200){
                    event.getHook().sendMessage("These are your words:\n\n **" + listWithoutDuplicates + "**").setEphemeral(true).queue();
                } else {
                    event.getHook().sendMessage("We did it, but there's a fuck ton of words lol").setEphemeral(true).queue();
                }
                Validate.validWords.clear();
            } else {
                event.getHook().sendMessage("your letters don't meet the criteria, please try again").setEphemeral(true).queue();
            }
        } else if(event.getName().equals("define")){
            event.deferReply().queue();

            OptionMapping wordReal = event.getOption("word");

            if(wordReal == null){
                event.getHook().sendMessage("your word gave an error, please try again").setEphemeral(true).queue();
                return;
            }

            String word = wordReal.getAsString();
            
            try {
                String[] list = Dictionary.findDefinition(word);

                if(list != null){
                    event.getHook().sendMessage("The word " + list[0] + " means: \n\n**" + list[1] + "**").setEphemeral(true).queue();
                } else {
                    event.getHook().sendMessage("That is probably not a word lol. Try looking it up on urban dictionary maybe?").setEphemeral(true).queue();
                }
            } catch (Exception e) {
                event.getHook().sendMessage("An unexpected error occured.").setEphemeral(true).queue();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("what")) {
            event.getChannel().sendMessage("https://tenor.com/view/saving-for-later-also-spongebob-melt-haha-gif-25651594").queue();
        }
    }
}