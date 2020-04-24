package com.xc.Tank;

import java.io.IOException;
import java.util.Properties;

public class ConfigMgr {

    public static Properties prop = null;

    static {
        try {
            prop = new Properties();
            prop.load(ConfigMgr.class.getClassLoader().getResourceAsStream("config"));

        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

//    public void ConfigMgr() {
//
//
//    }
}
