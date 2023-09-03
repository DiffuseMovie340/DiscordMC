package com.diffusemovie.dmc.commands;

import com.diffusemovie.dmc.DiscordMC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class TuneCommand implements CommandExecutor {
    private DiscordMC main;

    public TuneCommand(DiscordMC main){
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){

            Player player = (Player) sender;

            if (main.getConfigManager().getConfigBoolean() == true){

                main.getConfigManager().setPlayerConfig(player.getUniqueId(), args[0]);
                player.sendMessage(ChatColor.GOLD + "Channel successfully set- if it exists, then you will receive this channel's messages.");

            }else{

                player.sendMessage(ChatColor.RED + "DiscordMC has not been set up on this server!");
                player.sendMessage(ChatColor.RED + "Please ask a server operator (op) to run /config and set it up.");

            }

        }else{

            System.out.println("Sorry, you must be a player to use this command.");

        }

        return false;
    }
}
