package com.xc.Tank;

public class main {
    public static void main(String[] argus){
        //TankFrame myFrame = new TankFrame();

        TankFrame.INSTRANCE.setVisible(true);

        while(true){
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException ex){
                ex.printStackTrace();

            }
            TankFrame.INSTRANCE.repaint();

        }
    }
}
