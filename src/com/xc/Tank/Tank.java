package com.xc.Tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x ,y ;
    private Dir dir ;
    private Group group;

    private int tankWidth,tankHeight;
    private int bulletWidth,bulletHeight;

    private boolean BU,BD,BL,BR;

    private final int SPEED = 10;

    private boolean stop = true;

    public Tank(int x,int y,Dir dir,Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        tankWidth = ResourceMgr.goodTankU.getWidth();
        tankHeight = ResourceMgr.goodTankU.getHeight();
        bulletWidth = ResourceMgr.bulletU.getWidth();
        bulletHeight = ResourceMgr.bulletU.getHeight();
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void paint(Graphics g) {
        move();

        switch(dir){
            case U:
                g.drawImage(ResourceMgr.goodTankU,x,y,null);
                break;
            case D:
                g.drawImage(ResourceMgr.goodTankD,x,y,null);
                break;
            case L:
                g.drawImage(ResourceMgr.goodTankL,x,y,null);
                break;
            case R:
                g.drawImage(ResourceMgr.goodTankR,x,y,null);
                break;
        }

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
            case KeyEvent.VK_CONTROL:
                fire();
                break;

        }
        getDir();
    }

    private void fire() {
        Bullet bullet;
        if (dir == Dir.U || dir == Dir.D){
            bullet = new Bullet(x + (tankWidth - bulletWidth)/2,y + (tankHeight - bulletHeight)/2,dir,group);
        }
        else {
            bullet = new Bullet(x + (tankWidth - bulletWidth)/2,y + (tankHeight - bulletHeight)/2, dir, group);
        }

        TankFrame.INSTRANCE.addBullet(bullet);

        bullet.move();

    }

    private void getDir() {
        stop = false;
        if (BU&&!BD&&!BR&&!BL)
            dir = Dir.U;
        else if (!BU&&BD&&!BR&&!BL)
            dir = Dir.D;
        else if (!BU&&!BD&&BR&&!BL)
            dir = Dir.R;
        else if (!BU&&!BD&&!BR&&BL)
            dir = Dir.L;
        else{
            stop = true;
        }

    }

    private void move() {
        if (stop) return;
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
        }
    }
}
