package com.josroossien.arvip;

import com.josroossien.arvip.commands.Commands;
import com.josroossien.arvip.config.PluginCfg;
import com.josroossien.arvip.events.MainEvents;
import com.josroossien.arvip.sql.MySql;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ARVip extends JavaPlugin {

    private static ARVip instance;

    private Commands cmds;

    private PluginCfg cfg;

    private MySql sql = null;
    private Connection c = null;

    private PlayerManager playerManager;

    private final Logger log = Logger.getLogger("Minecraft");


    @Override
    public void onDisable() {
        try {
            c.close();
            sql.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        log("disabled");
    }

    @Override
    public void onEnable() {
        instance = this;

        cfg = new PluginCfg("plugins/ARVote/ARVote.yml");
        cfg.load();

        sql = new MySql(this, cfg.SQL__ADDRESS, cfg.SQL__PORT, cfg.SQL__DATABASE, cfg.SQL__USER, cfg.SQL__PASS);
        c = sql.openConnection();
        if (c == null) {
            log("Can't connect to database!");
            getPluginLoader().disablePlugin(this);
            return;
        }

        playerManager = new PlayerManager(this);

        cmds = new Commands(this);

        registerEvents();

        log("loaded successfully");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return cmds.onCommand(sender, cmd, label, args);
    }


    public static ARVip inst() {
        return instance;
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new MainEvents(this), this);
    }

    public void log(Object msg) {
        log.info("[ARVip " + getDescription().getVersion() + "] " + msg.toString());
    }


    public PluginCfg getCfg() {
        return cfg;
    }

    public Connection getSql() {
        try {
            if (c == null || c.isClosed()) {
                c = sql.openConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public PlayerManager getPM() {
        return playerManager;
    }

}
