package com.josroossien.arvip.sql;

import com.josroossien.arvip.ARVip;

import java.sql.Connection;

public abstract class Database {

    /**
     * Plugin instance, use for plugin.getDataFolder() and plugin.getLogger()
     */
    protected ARVip plugin;

    /**
     * Creates a new Database
     * 
     * @param pl
     *            Plugin instance
     */
    protected Database(ARVip pl) {
        this.plugin = pl;
    }

    /**
     * Opens a connection with the database
     * 
     * @return Connection opened
     */
    public abstract Connection openConnection();

    /**
     * Checks if a connection is open with the database
     * 
     * @return true if a connection is open
     */
    public abstract boolean checkConnection();

    /**
     * Gets the connection with the database
     * 
     * @return Connection with the database, null if none
     */
    public abstract Connection getConnection();

    /**
     * Closes the connection with the database
     */
    public abstract void closeConnection();
}