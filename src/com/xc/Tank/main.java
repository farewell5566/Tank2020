package com.xc.Tank;

import com.xc.Tank.net.Client;

public class main {
    public static void main(String[] argus){
        //TankFrame myFrame = new TankFrame();

        TankFrame.INSTRANCE.setVisible(true);

        new Thread(()->{
        while(true){
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
            TankFrame.INSTRANCE.repaint();
        }}).start();

        Client.INSTANCE.Init();
    }
}
