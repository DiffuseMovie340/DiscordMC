package com.diffusemovie.dmc;

import com.diffusemovie.dmc.commands.*;
import com.diffusemovie.dmc.manager.ChatManager;
import com.diffusemovie.dmc.manager.ConfigManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;

public final class DiscordMC extends JavaPlugin {

    private JDA jda;

    private DiscordListener discordListener;
    private ConfigManager configManager;
    private ChatManager chatManager;
    private JoinVC joinVC;
    private File configFile;

    @Override
    public void onEnable() {

        configManager = new ConfigManager(this);
        chatManager = new ChatManager(this);
        joinVC = new JoinVC(this, jda);

        //Bot token goes here- but DiscordMC bot is provided in the main releases, with the invite link.
        JDABuilder builder = JDABuilder.createDefault("");
        builder.setActivity(Activity.watching("your servers, so you don't have to!"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListeners(new DiscordListener(this));
        try {
            jda = builder.build();
            System.out.println("Successfully finished task.");
        } catch (LoginException e) {
            e.printStackTrace();
        }

        if (!this.getDataFolder().exists()){
            this.getDataFolder().mkdir();
        }


        configManager.resetConfigStage();
        configManager.checkForConfig(null);

        getCommand("tune").setExecutor(new TuneCommand(this));
        getCommand("config").setExecutor(new ConfigCommand(this));
        getCommand("blockdiscord").setExecutor(new BlockDiscordCommand());
        getCommand("togglediscord").setExecutor(new DiscordToggleCommand());


    }





    public ConfigManager getConfigManager(){ return configManager; }
    public ChatManager getChatManager() { return chatManager; }
    public JDA getJda(){ return jda; }

}



 /*roadmap
    1.Add config file to get minecraft Server ID...DONE
    2./configdmc...DONE
    3.Boolean to prevent multiple config sessions at the same time...DONE
    4.Add player data file to store UUID, ChannelId tuned, and if Discord is toggled.
    5.For loop to loop through values of the data file, so when a message is sent, show it to people tuned to that
    channel
    6./tune <Channel> command
    7./toggledmc <on/off>
    8.PlayerAsyncChatEvent sends message to Discord channel tuned to if Discord enabled
    9./register <name> command
    10.Banned players
    11.VC capability?
    12.Syncing roles?

    */