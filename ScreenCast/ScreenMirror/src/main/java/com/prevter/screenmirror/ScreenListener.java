package com.prevter.screenmirror;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScreenListener implements Listener{
    
    public ScreenListener(App plugin){
        
    }

    //Events
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().sendMessage("hello world!");
    }
}