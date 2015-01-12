package com.josroossien.arvip.commands;

import com.josroossien.arvip.ARVip;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Commands {

    private ARVip arv;

    public Commands(ARVip arv) {
        this.arv = arv;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }

}
