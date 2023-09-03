package com.diffusemovie.dmc.commands;

import com.diffusemovie.dmc.DiscordMC;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ConfigCommand implements CommandExecutor {
    private DiscordMC main;
    public ConfigCommand(DiscordMC main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (sender instanceof Player == false || sender.isOp()){
           if (main.getConfigManager().getStage() == 0) {
               //stage 1
               //runs if config is not active
               if (main.getConfigManager().getConfigActive() == false) {
                   if (sender instanceof Player) {

                       TextComponent stage11 = new TextComponent("§9Hello, welcome to DiscordMC config!\nTo begin, " +
                               "§csend the ID (not name) of this server's Discord server with /config <id>. ");
                       TextComponent stage12 = new TextComponent("§3§l[Hover for help]");
                       stage12.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("To get the ID, go to User" +
                               " settings (By your username) -> Advanced\n and turn the developer mode slider on.\nThen, " +
                               "right-click your desired server's icon and click Copy ID.")));

                       sender.spigot().sendMessage(new BaseComponent[]{stage11, stage12});
                   } else {

                       System.out.println("Hello, welcome to DiscordMC config!");
                       System.out.println("To begin, send the ID (not name) of the Discord server that will be linked with /config <id>. ");
                       System.out.println("Help: to get the ID, go to User settings (By your username)");
                       System.out.println(" -> Advanced and turn the developer mode slider on.Then, right-click your desired server's icon and click Copy ID.");

                   }

                   main.getConfigManager().incrementConfigStage(1);

               } else {
                   if (sender instanceof Player) {

                       sender.sendMessage(ChatColor.RED + "Another config session is active! Please wait.");

                   } else {

                       System.out.println("Another config session is active! Please wait.");

                   }
               }
           }else if (main.getConfigManager().getStage() == 1){
               main.getConfigManager().setConfigFile(1, args[0]);
               if (sender instanceof Player){

                   TextComponent stage2 = new TextComponent("§9The second step is to give the default (Text) channel, like as a general channel. "
                   + "\n §cType /config <channelName> without the # in the channel's name.");

                   sender.spigot().sendMessage(new BaseComponent[]{stage2});
               }else{

                   System.out.println("The second step is to give the default (Text) channel, like as a general channel.");

                   System.out.println("Type /config <channelName> without the # in the channel's name.");
               }

               main.getConfigManager().incrementConfigStage(1);

           }else if (main.getConfigManager().getStage() == 2){

               main.getConfigManager().setConfigFile(2, args[0]);

               if (sender instanceof Player){

                TextComponent stage31 = new TextComponent("§9Thank you. The last step is to invite the DiscordMC bot to the desired server: ");
                TextComponent stage32 = new TextComponent("§c[Click for link]");

                stage32.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/oauth2/authorize?client_id=991656076145606676&scope=bot&permissions=309448412160"));

                TextComponent stage33 = new TextComponent("\n§9Or just type this link: https://discord.com/oauth2/authorize?client_id=991656076145606676&scope=bot&permissions=309448412160");
                TextComponent stage34 = new TextComponent("\nOnce over, then simply type /config to confirm completion!");

                sender.spigot().sendMessage(new BaseComponent[]{stage31, stage32, stage33, stage34});
              }else{
                   System.out.println("Thank you. The last step is to invite the DiscordMC bot to the desired server.");
                   System.out.println("Use this link: https://discord.com/oauth2/authorize?client_id=991656076145606676&scope=bot&permissions=309448412160");
                   System.out.println("Once over, simply type /config to confirm completion!");
              }
              main.getConfigManager().incrementConfigStage(1);
               main.getConfigManager().saveConfigFile();
           }else if (main.getConfigManager().getStage() == 3){
               if (sender instanceof Player){

                   sender.sendMessage(ChatColor.GOLD + "Thank you for completing config! Your Discord server's messages will appear in-game.");

               }else{

                   System.out.println("Thank you for completing config! Your Discord server's messages will appear in-game.");
               }

           }


        }

        return false;
    }


}
