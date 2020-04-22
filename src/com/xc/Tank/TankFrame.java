package com.xc.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankFrame extends Frame {

    Tank myTank;


    public TankFrame() {
        this.setLocation(100,100);
        this.setSize(800,1000);
        this.addKeyListener(new MyTankListener());
        myTank = new Tank();
    }



    @Override
    public void paint(Graphics g) {
        myTank.paint(g);

    }

    private class MyTankListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.KeyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e){
            myTank.keyReleased(e);
        }
    }
}
