package com.diffusemovie.dmc.manager;

import com.diffusemovie.dmc.DiscordMC;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ConfigManager {

    private DiscordMC main;

    private File configFile;
    private YamlConfiguration config;

    private File playerSettings;
    private YamlConfiguration playerConfig;

    private File blockedPlayers;
    private YamlConfiguration blockedConfig;


    String guildID;
    String defaultChannel;
    public int configStage;

    public boolean configSessionActive;


    public ConfigManager(DiscordMC main){
        this.main = main;

    }

    //access/set files
    public void initiateFile(String fileName){

        File file = new File(main.getDataFolder(), fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void setConfigFile(int stage,String value){


        if (stage == 1){
            guildID = value;
        }else if (stage == 2){
            defaultChannel = value;
        }
    }
    public void saveConfigFile(){

        configFile = new File(main.getDataFolder(), "config.yml");
        if (!configFile.exists()){
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        config.set(guildID, defaultChannel);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setPlayerConfig(UUID uuid, String tunedChannel){
        playerSettings = new File(main.getDataFolder() ,"playerConfig.yml");

        if (!playerSettings.exists()){
            try {
                playerSettings.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        playerConfig = YamlConfiguration.loadConfiguration(playerSettings);
        playerConfig.set(uuid.toString(), tunedChannel);

        try {
            playerConfig.save(playerSettings);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setBlockedPlayer(UUID uuid, boolean isBlocked){
        blockedPlayers = new File(main.getDataFolder(), "blockedPlayers.yml");

        if (!blockedPlayers.exists()){
            try {
                blockedPlayers.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        blockedConfig = YamlConfiguration.loadConfiguration(blockedPlayers);
        blockedConfig.set(uuid.toString(), isBlocked);

        try {
            blockedConfig.save(blockedPlayers);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    //getter methods
    public int getStage(){ return configStage; }

    public void resetConfigStage(){ configStage = 0; }

    public void incrementConfigStage(int value){ configStage = configStage + value; }

    public String getGuildID(){ return config.getString(guildID); }

    public void checkForConfig(Player player){
        if (!configFile.exists()){
            if (player == null) {
                System.out.println("Please run /config to setup your server's DiscordMC client.");
            }else{
                player.sendMessage(ChatColor.RED +
                        "A server operator needs to run /config to setup the server DiscordMC client!");
            }
        }
    }

    public boolean getConfigBoolean(){
        if (configFile.exists()){
            return true;
        }else{
            return false;
        }
    }

    public boolean getConfigActive(){return configSessionActive;}

    public boolean playerBanned(UUID uuid){

        return false;
    }



    public YamlConfiguration getPlayerConfig() { return playerConfig; }

    public YamlConfiguration getBlockedConfig() { return blockedConfig; }

    public YamlConfiguration getConfig() { return config; }
    public File getFile(String name){
        if (name == "playerSettings"){
            return playerSettings;
        }else if (name == "configFile"){
            return configFile;
        }else if (name == "blockedPlayers"){
            return blockedPlayers;
        }else{
            return null;
        }
    }








}

