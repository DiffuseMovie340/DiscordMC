package com.diffusemovie.dmc;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Member;

public class JoinVC {
    //class is a resource for vc support
    private DiscordMC main;
    private JDA jda;

    public JoinVC(DiscordMC main, JDA jda){
        this.main = main;

    }
    public void addMember(){

        AudioChannel vc = jda.getGuildById("").getVoiceChannelById("");
        Member member = jda.getGuildById("").getMemberById("");
        if (jda.getGuildById("").getMembers().contains(jda.getGuildById("").getMemberById(""))){

        }

    }









}
