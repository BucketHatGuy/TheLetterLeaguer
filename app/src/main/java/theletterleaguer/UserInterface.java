package theletterleaguer;

import java.io.IOException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class UserInterface {
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
            .build().awaitReady();

        try {
            jda.upsertCommand("scramble","scramble your letters to find available words").addOption(OptionType.STRING, "letters", "letters you want to scramble", true).queue();
            jda.upsertCommand("define","give the definition to a word").addOption(OptionType.STRING, "word", "word you want to find a definition to", true).queue();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}