package com.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;
import org.jetbrains.annotations.NotNull;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bot.Command.*;

public class App extends ListenerAdapter {

    private final Map<String, SlashCommand> commands = new HashMap<>();

    public static void main(String[] args) throws LoginException {

        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");

        try {
            JDA jda = JDABuilder.createDefault(token)
                    .setActivity(Activity.playing("with fire"))
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build();

            jda.awaitReady();

            // register command
            App main = new App();
            main.registerCommand(new Ping());
            main.registerCommand(new Say());
            main.registerCommand(new YuhiFace());
            List<SlashCommandData> cmds = main.commands.values().stream().map(SlashCommand::getCommandData).toList();

            String id = dotenv.get("TEST_SERVER_ID");
            Guild guild = jda.getGuildById(id);

            if (guild != null) {
                guild.updateCommands().addCommands(cmds).queue();
            } else {
                jda.updateCommands().addCommands(cmds).queue();
            }
            jda.addEventListener(main);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerCommand(SlashCommand command) {
        commands.put(command.getCommandName(), command);
    }

    // respond to messages
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;
        String message = event.getMessage().getContentRaw();
        if (message.equals("!hello")) {
            event.getChannel().sendMessage("おはこんばんちわ").queue();
            event.getMessage().reply("んちゃ☆")
                    .mentionRepliedUser(false)
                    .queue();
        } else if (message.equals("ぬるぽ")) {
            event.getMessage().reply("ｶﾞｯ")
                    .mentionRepliedUser(false)
                    .queue();
        }
    }

    // respond to slash-command
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        SlashCommand command = commands.get(event.getName());
        if (command != null) {
            command.execute(event);
        }
    }
}
