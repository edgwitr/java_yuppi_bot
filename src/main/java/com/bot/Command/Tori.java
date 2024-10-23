package com.bot.Command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import net.dv8tion.jda.api.EmbedBuilder;

public class Tori implements SlashCommand {
    @Override
    public String getDescription() {
        return "reply tori";
    }

    @Override
    public SlashCommandData getCommandData() {
        return Commands.slash(this.getCommandName(), this.getDescription());
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // event.deferReply().queue();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("tori");
        embed.setImage("https://media.tenor.com/8AqUPOC5GMgAAAAi/parrot-party.gif");

        event.replyEmbeds(embed.build()).queue(); // 埋め込みメッセージを送信
    }

}
