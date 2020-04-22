package com.xc.Tank;

public class main {
    public static void main(String[] argus){
        TankFrame myFrame = new TankFrame();

        myFrame.setVisible(true);

        while(true){
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException ex){
                ex.printStackTrace();

            }
            myFrame.repaint();

        }
    }
}
