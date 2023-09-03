package com.diffusemovie.dmc;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.UUID;

public class DiscordListener extends ListenerAdapter {
    private DiscordMC main;
    public DiscordListener(DiscordMC main){
        this.main = main;
    }

    private JDA jda;



    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        List recipients = main.getChatManager().getTunedPlayers(e.getTextChannel());
        for (int i = 0; i <= recipients.size(); i++){
            Bukkit.getPlayer((UUID) recipients.get(i)).sendMessage(e.getAuthor() + " : " + e.getMessage());
        }
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        TextChannel textChannel = jda.getGuildById(main.getConfigManager().getGuildID()).getTextChannelById(main.getChatManager().getPlayerChannel(e.getPlayer()));
        textChannel.sendMessage(e.getPlayer() + ":" + e.getMessage()).queue();

    }
    public void onJoin(PlayerJoinEvent e){
        main.getConfigManager().checkForConfig(e.getPlayer());
    }


}
