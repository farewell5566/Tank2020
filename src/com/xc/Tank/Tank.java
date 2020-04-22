package com.xc.Tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x = 100,y = 100;

    private boolean BU,BD,BL,BR;

    private final int SPEED = 10;
    private Dir dir ;

    public void paint(Graphics g) {
        g.fillRect(x,y,20,20);
    }

    public void KeyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                BU = true;
                break;
            case KeyEvent.VK_DOWN:
                BD = true;
                break;
            case KeyEvent.VK_RIGHT:
                BR = true;
                break;
            case KeyEvent.VK_LEFT:
                BL = true;
                break;
        }
        getDir();
    }

    public void keyReleased(KeyEvent e) {
        int key =e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                BU = false;
                break;
            case KeyEvent.VK_DOWN:
                BD = false;
                break;
            case KeyEvent.VK_RIGHT:
                BR = false;
                break;
            case KeyEvent.VK_LEFT:
                BL = false;
                break;
        }
        getDir();
    }

    private void getDir() {
        if (BU&&!BD&&!BR&&!BL)
            dir = Dir.U;
        else if (!BU&&BD&&!BR&&!BL)
            dir = Dir.D;
        else if (!BU&&!BD&&BR&&!BL)
            dir = Dir.R;
        else if (!BU&&!BD&&!BR&&BL)
            dir = Dir.L;
        else
            dir = Dir.S;
        move();

    }

    private void move() {
        switch(dir){
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case L:
                x -= SPEED;
                break;
            case S:
                break;

        }
    }
}
