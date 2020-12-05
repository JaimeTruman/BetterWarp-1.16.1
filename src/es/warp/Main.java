package es.warp;

import es.warp.commands.CommandManager;
import es.warp.eventos.PlayerInventoryClick;
import es.warp.objects.WarpManager;
import es.warp.objects.WarpPoint;
import es.warp.util.FilesSerialization;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class Main extends JavaPlugin {
    private File warpsDat;
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        setUp();
        setUpCommands();
        setUpEvents();
    }

    private void setUp() {
        File pluginFile = new File(getDataFolder().getAbsolutePath());
        warpsDat = new File(pluginFile.getAbsolutePath() + File.separator + "warps.dat");

        if(pluginFile.exists()){
            WarpManager.setUpWarps(warpsDat);
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Se han cargado todos los warps");
        }else{
            createPluginFile(pluginFile);
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Se ha creado el archivo de los warps");
        }
    }

    private void createPluginFile(File pluginPath){
        try {
            pluginPath.mkdirs();

            File file = new File(pluginPath.getAbsolutePath() + File.separator + "warps.dat");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpCommands () {
        CommandManager commandExecutor = new CommandManager();
        getCommand("warp").setExecutor(commandExecutor);
        getCommand("addwarp").setExecutor(commandExecutor);
        getCommand("deletewarp").setExecutor(commandExecutor);
    }

    private void setUpEvents () {
        getServer().getPluginManager().registerEvents(new PlayerInventoryClick(), this);
    }

    @Override
    public void onDisable() {
        try {
            FilesSerialization.serialize(WarpManager.getCopyOfWarps(), warpsDat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}