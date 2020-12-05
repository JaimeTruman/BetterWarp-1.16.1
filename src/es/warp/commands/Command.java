package es.warp.commands;

import org.bukkit.entity.Player;

public abstract class Command {
    public abstract String getCommandName();
    public abstract void executeCommand (Player player, String[] args);
}