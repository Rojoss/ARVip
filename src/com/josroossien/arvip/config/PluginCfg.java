package com.josroossien.arvip.config;

import com.clashwars.cwcore.config.internal.EasyConfig;

public class PluginCfg extends EasyConfig {

    public String SQL__ADDRESS = "address";
    public String SQL__PORT = "3306";
    public String SQL__DATABASE = "ARVip";
    public String SQL__USER = "user";
    public String SQL__PASS = "pass";

    public PluginCfg(String fileName) {
        this.setFile(fileName);
    }

}
