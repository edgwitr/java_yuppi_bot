package com.bot.Command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class YuhiFace implements SlashCommand {
    @Override
    public String getDescription() {
        return "YuhiFace";
    }

    @Override
    public SlashCommandData getCommandData() {
        return Commands.slash(this.getCommandName(), this.getDescription())
                .addOption(OptionType.STRING, "content", "これを喋る", true);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String content = event.getOption("content").getAsString();
        event.reply("""
                ```
                ┌-----------------┐
                |                 |
                | """ + content + """
                |
                |                 |
                └--------v--------┘
                     ＿＿＿＿
                 　／        ＼
                  /  ^ーーー^　Λ
                くL | ´・ヮ・`　|＞
                　L_|`ーーーーヒ」
                ```

                                """).queue();
    }

}
