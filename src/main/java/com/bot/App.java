package com.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;
import org.jetbrains.annotations.NotNull;
import io.github.cdimascio.dotenv.Dotenv;

public class App extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {

        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");

        try {
            JDA jda = JDABuilder.createDefault(token)
                .setActivity(Activity.playing("with fire"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

            jda.awaitReady();

            SlashCommandData pingCommand = Commands.slash("ping", "pingpon");

            String id = dotenv.get("TEST_SERVER_ID");
            Guild guild = jda.getGuildById(id);

            guild.updateCommands().addCommands(pingCommand).queue();

            jda.addEventListener(new App());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // respond to messages
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        if (message.equals("!hello")) {
            event.getChannel().sendMessage("おはこんばんちわ").queue();
            event.getMessage().reply("んちゃ☆")
                .mentionRepliedUser(false)
                .queue();
        }
    }

    // respond to slash-command
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            long gatewayPing = event.getJDA().getGatewayPing();
            event.reply("Pon! ゲートウェイのpingは " + gatewayPing + " msでした。")
                // .setEphemeral(true)
                .queue();
        }
    }
}
