package com.xc.Tank;

import com.xc.Tank.Strategy.FireStrategy;
import com.xc.Tank.Strategy.FireStrategyDefault;
import com.xc.Tank.net.JoinMsg;

import java.awt.*;
import java.util.UUID;

public class Tank extends AbstractGameObject{
    private int x ,y ;
    private Dir dir ;
    private Group group;
    private boolean stop;

    private int tankWidth,tankHeight;
    private int bulletWidth,bulletHeight;

    private final int SPEED = 15;
    private boolean isLive = true;
    private int oldX ,oldY;

    private Rectangle rect ;
    private UUID id = null;

    public Tank(int x,int y,Dir dir,Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        tankWidth = ResourceMgr.goodTankU.getWidth();
        tankHeight = ResourceMgr.goodTankU.getHeight();
        bulletWidth = ResourceMgr.bulletU.getWidth();
        bulletHeight = ResourceMgr.bulletU.getHeight();
        rect = new Rectangle(x,y,tankWidth,tankHeight);
    }
    public Tank(JoinMsg msg){
        this.x= msg.getX();
        this.y =msg.getY();
        this.dir = msg.getDir();
        this.group = msg.getGroup();
        this.stop = !msg.isMoving();
        this.id = msg.getId();
        tankWidth = ResourceMgr.goodTankU.getWidth();
        tankHeight = ResourceMgr.goodTankU.getHeight();
        bulletWidth = ResourceMgr.bulletU.getWidth();
        bulletHeight = ResourceMgr.bulletU.getHeight();
        rect = new Rectangle(x,y,tankWidth,tankWidth);
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

    public UUID getId() {
        return id;
    }

    public void paint(Graphics g) {
        if (!isLive) return;

        move();
        rect.x = x;
        rect.y = y;
        boundIntersect();
        randomDir();

        if (Math.random()*100>90){
            fire();
        }

        switch(dir){
            case U:
                g.drawImage(group == Group.BAD? ResourceMgr.badTankU:ResourceMgr.goodTankU,x,y,null);
                break;
            case D:
                g.drawImage(group == Group.BAD?ResourceMgr.badTankD:ResourceMgr.goodTankD,x,y,null);
                break;
            case L:
                g.drawImage(group == Group.BAD?ResourceMgr.badTankL:ResourceMgr.goodTankL,x,y,null);
                break;
            case R:
                g.drawImage(group == Group.BAD?ResourceMgr.badTankR:ResourceMgr.goodTankR,x,y,null);
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

        TankFrame.INSTRANCE.getGm().add(bullet);

    }

    private void randomDir() {
        if (Math.random()*100 > 90)
            dir = Dir.getRandom();

    }

    private void move() {
        if (stop) return;
        oldX = this.x;
        oldY = this.y;
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
        if (x<0||y<30||x+tankWidth > TankFrame.INSTRANCE.getGameWidth() || y + tankHeight>TankFrame.INSTRANCE.getGameHeight()) {
            x = oldX;
            y= oldY;
        }
    }

    public Rectangle getRect() {
        return this.rect;
    }

    public void back() {
        x = oldX;
        y = oldY;
    }

    @Override
    public String toString() {
        return "Tank{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", stop=" + stop +
                ", tankWidth=" + tankWidth +
                ", tankHeight=" + tankHeight +
                ", bulletWidth=" + bulletWidth +
                ", bulletHeight=" + bulletHeight +
                ", SPEED=" + SPEED +
                ", isLive=" + isLive +
                ", oldX=" + oldX +
                ", oldY=" + oldY +
                ", rect=" + rect +
                '}';
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public Group GetGroup() {
        return group;
    }
}
