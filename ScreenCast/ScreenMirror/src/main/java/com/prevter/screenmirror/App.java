package com.prevter.screenmirror;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;

public class App extends JavaPlugin {

    private File path = new File(this.getDataFolder() + "/image");

    public long speed = 1L;

    @Override
    public void onEnable() {
        getLogger().info("Starting screen mirror. Make sure to run python script!");
        PluginManager pm = getServer().getPluginManager();
        ScreenListener listener = new ScreenListener(this);
        pm.registerEvents(listener,this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                getLogger().info("Screen Tick with speed "+speed);
                try {
                    for (File file : path.listFiles()){
                        JSONParser jsonParser = new JSONParser();
                        Object parsed = jsonParser.parse(new FileReader(file.getPath()));
                        JSONObject jsonObject = (JSONObject) parsed;
                        JSONArray rows = (JSONArray) jsonObject.get("image"); // Get all JSONArray data
                        for(int y = 0; y < 90; y++){
                            JSONArray line = (JSONArray) rows.get(y);
                            for(int x = 0; x < 160; x++){
                                Block currBlock = getServer().getWorld("world").getBlockAt(x, 130-y, 0);
                                switch (line.get(x).toString()){
                                    case "0":
                                        if(currBlock.getType() != Material.STONE) currBlock.setType(Material.STONE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "1":
                                        if(currBlock.getType() != Material.DIRT) currBlock.setType(Material.DIRT);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "2":
                                        if(currBlock.getType() != Material.COBBLESTONE) currBlock.setType(Material.COBBLESTONE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "3":
                                        currBlock.setType(Material.WOOD);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "4":
                                        currBlock.setType(Material.BEDROCK);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "5":
                                        currBlock.setType(Material.GOLD_ORE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "6":
                                        currBlock.setType(Material.IRON_ORE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "7":
                                        currBlock.setType(Material.COAL_ORE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "8":
                                        currBlock.setType(Material.LOG);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "9":
                                        currBlock.setType(Material.SPONGE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "10":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 0);
                                        break;
                                    case "11":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 1);
                                        break;
                                    case "12":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 2);
                                        break;
                                    case "13":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 3);
                                        break;
                                    case "14":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 4);
                                        break;
                                    case "15":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 5);
                                        break;
                                    case "16":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 6);
                                        break;
                                    case "17":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 7);
                                        break;
                                    case "18":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 8);
                                        break;
                                    case "19":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 9);
                                        break;
                                    case "20":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 10);
                                        break;
                                    case "21":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 11);
                                        break;
                                    case "22":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 12);
                                        break;
                                    case "23":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 13);
                                        break;
                                    case "24":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 14);
                                        break;
                                    case "25":
                                        currBlock.setType(Material.WOOL);
                                        currBlock.setData((byte) 15);
                                        break;
                                    case "26":
                                        currBlock.setType(Material.GOLD_BLOCK);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "27":
                                        currBlock.setType(Material.IRON_BLOCK);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "28":
                                        currBlock.setType(Material.BRICK);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "29":
                                        currBlock.setType(Material.MOSSY_COBBLESTONE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "30":
                                        currBlock.setType(Material.OBSIDIAN);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "31":
                                        currBlock.setType(Material.DIAMOND_ORE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "32":
                                        currBlock.setType(Material.DIAMOND_BLOCK);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "33":
                                        currBlock.setType(Material.WORKBENCH);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "34":
                                        currBlock.setType(Material.REDSTONE_ORE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "35":
                                        currBlock.setType(Material.SNOW_BLOCK);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "36":
                                        currBlock.setType(Material.CLAY);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "37":
                                        currBlock.setType(Material.JUKEBOX);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "38":
                                        currBlock.setType(Material.NETHERRACK);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "39":
                                        currBlock.setType(Material.SOUL_SAND);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "40":
                                        currBlock.setType(Material.GLOWSTONE);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "41":
                                        currBlock.setType(Material.BOOKSHELF);
                                        //currBlock.setData((byte) 15);
                                        break;
                                    case "42":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 0);
                                        break;
                                    case "43":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 1);
                                        break;
                                    case "44":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 2);
                                        break;
                                    case "45":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 3);
                                        break;
                                    case "46":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 4);
                                        break;
                                    case "47":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 5);
                                        break;
                                    case "48":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 6);
                                        break;
                                    case "49":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 7);
                                        break;
                                    case "50":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 8);
                                        break;
                                    case "51":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 9);
                                        break;
                                    case "52":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 10);
                                        break;
                                    case "53":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 11);
                                        break;
                                    case "54":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 12);
                                        break;
                                    case "55":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 13);
                                        break;
                                    case "56":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 14);
                                        break;
                                    case "57":
                                        currBlock.setType(Material.CONCRETE);
                                        currBlock.setData((byte) 15);
                                        break;
                                    default:
                                        getLogger().info("["+y+","+x+"] Unknown type "+line.get(x).toString());
                                        currBlock.setType(Material.AIR);
                                        break;
                                }
                            }
                        }
                    }
                } catch (ParseException | IOException e){
                    e.printStackTrace();
                }
                
            }
        }, 0L, speed);
    }
    @Override
    public void onDisable() {
        getLogger().info("Closing...");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;

        if (sender instanceof Player){
            String lowerCmd = cmd.getName().toLowerCase();
            switch(lowerCmd){
                case "freq":
                    speed = Long.parseLong(args[0]);
                    player.sendMessage("Screen timings set to "+speed);
                    return true;
            }
            return true;
        }

        //player.sendMessage("");
        return true;
    }
    
}