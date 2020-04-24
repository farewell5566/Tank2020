package com.xc.Tank;

import java.util.Properties;

public class Test {
    public static void main(String[]argus){
        try {
            Properties prop = new Properties();
            prop.load(Test.class.getClassLoader().getResourceAsStream("config"));
            String num = (String)prop.get("initTankNum");
            System.out.println(num);

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

}
