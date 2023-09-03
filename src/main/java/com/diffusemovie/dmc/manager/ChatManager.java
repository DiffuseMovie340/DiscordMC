package com.diffusemovie.dmc.manager;

import com.diffusemovie.dmc.DiscordMC;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ChatManager {
    private DiscordMC main;

    public ChatManager(DiscordMC main){
        this.main = main;
    }

    YamlConfiguration playerConfig = main.getConfigManager().getPlayerConfig();
    File playerSettings = main.getConfigManager().getFile("playerSettings");

    public String getPlayerChannel(Player player){


        try {
            playerConfig.load(playerSettings);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        String channel = (String) playerConfig.getValues(true).get(player.getUniqueId().toString());
        return channel;


    }
    public List getTunedPlayers(TextChannel channelName){
        try {
            playerConfig.load(playerSettings);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        List<UUID> tunedPlayers = new ArrayList<>();
        HashMap players = (HashMap) playerConfig.getValues(true);

        for (Player target : Bukkit.getOnlinePlayers()){
            if (players.get(target.getUniqueId().toString()) == channelName){
                tunedPlayers.add(target.getUniqueId());
            }
        }
        return tunedPlayers;

    }
}
