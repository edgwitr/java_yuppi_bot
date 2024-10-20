package com.bot.Command;

import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SlashCommand {
    default String getCommandName() {
        Class<?> myClass = this.getClass();
        return myClass.getSimpleName().toLowerCase();
    };

    String getDescription();

    SlashCommandData getCommandData();

    void execute(SlashCommandInteractionEvent event);
}
