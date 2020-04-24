package com.xc.Tank;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Bullet {

    private static int SPEED = 30;
    private int x,y;
    private Dir dir;
    private Group group;
    private static int width,height;
    private Boolean isLive = true;


    public Bullet(){}

    public void setLive(boolean isLive){
        this.isLive = isLive;
    }

    public boolean getLive() {
        return isLive;
    }

    public Bullet(int x,int y,Dir dir,Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.width = ResourceMgr.bulletU.getWidth();
        this.height = ResourceMgr.bulletU.getHeight();
    }

    public void move() {
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

    public void paint(Graphics g) {
        if (!isLive) return ;
        switch(dir){
            case U:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case L:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
        }
        move();
    }

    public  void isOutBound() {
        if (x<0||y<0||x>TankFrame.GAME_WIDTH ||y>TankFrame.GAME_HEIGHT)
            this.isLive = false;
        else
           this.isLive = true;
    }

    public boolean collideWith(Tank tank){
        if (!tank.getLive()||!this.isLive) return false;
        if (this.group == tank.getGroup()) return false;
        Rectangle bulletRect = new Rectangle();
        bulletRect.setBounds(this.x,this.y,this.width,this.height);

        Rectangle tankRect = new Rectangle();
        tankRect.setBounds(tank.getX(),tank.getY(),ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
        if (bulletRect.intersects(tankRect)){
            TankFrame.INSTRANCE.addBoom(new Boom(tank.getX(),tank.getY(),true));
            return true;
        }
        else
            return false;

    }

}
