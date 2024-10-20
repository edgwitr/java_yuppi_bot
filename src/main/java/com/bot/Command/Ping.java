package com.bot.Command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Ping implements SlashCommand {
    @Override
    public String getDescription() {
        return "measure responce time";
    }

    @Override
    public SlashCommandData getCommandData() {
        return Commands.slash(this.getCommandName(), this.getDescription());
    };

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        long gatewayPing = event.getJDA().getGatewayPing();
        event.reply("Pon! Gateway ping is " + gatewayPing + " ms.")
                .setEphemeral(true)
                .queue();
    }

}
