package com.xc.Tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x ,y ;
    private Dir dir ;
    private Group group;
    private boolean stop;

    private int tankWidth,tankHeight;
    private int bulletWidth,bulletHeight;

    private final int SPEED = 15;
    private boolean isLive = true;
    private int oldX ,oldY;


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

    public void setLive(boolean isLive){
        this.isLive = isLive;
    }

    public boolean getLive() {
        return isLive;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void paint(Graphics g) {
        if (!isLive) return;
        oldX = this.x;
        oldY = this.y;
        move();
        boundIntersect();
        randomDir();
        switch(dir){
            case U:
                g.drawImage(ResourceMgr.badTankU,x,y,null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD,x,y,null);
                break;
            case L:
                g.drawImage(ResourceMgr.badTankL,x,y,null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR,x,y,null);
                break;
        }

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

    private void randomDir() {
        if (Math.random()*100 > 90)
            dir = Dir.getRandom();

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

    private void boundIntersect() {
        if (x<0||y<30||x+tankWidth > TankFrame.INSTRANCE.GAME_WIDTH || y + tankHeight>TankFrame.INSTRANCE.GAME_HEIGHT) {
            x = oldX;
            y= oldY;
        }

        //TankFrame.INSTRANCE.GAME_WIDTH

    }

}
