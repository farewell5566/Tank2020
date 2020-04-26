package com.xc.Tank;

import com.xc.Tank.Strategy.FireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends AbstractGameObject{
    private int x ,y ;
    private Dir dir ;
    private Group group;

    private int tankWidth,tankHeight;
    private int bulletWidth,bulletHeight;

    private boolean BU,BD,BL,BR;

    private final int SPEED = 15;
    private boolean isLive = true;

    private boolean stop = true;
    private FireStrategy fireStra = null;
    private Rectangle rect ;

    public Player(int x, int y, Dir dir, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        tankWidth = ResourceMgr.goodTankU.getWidth();
        tankHeight = ResourceMgr.goodTankU.getHeight();
        bulletWidth = ResourceMgr.bulletU.getWidth();
        bulletHeight = ResourceMgr.bulletU.getHeight();
        rect = new Rectangle(x,y,tankWidth,tankHeight);
        this.initFireStrategy();
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
        move();
        rect.x = x;
        rect.y = y;
        if (this.group == Group.GOOD){
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
        else if (this.group == Group.BAD){
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

    private void initFireStrategy(){
        String fireStr = (String)ConfigMgr.prop.get("FIRESTRATEGY");
        ClassLoader loader = Player.class.getClassLoader();
        try {
            Class clazz =loader.loadClass("com.xc.Tank.Strategy."+ fireStr);
            fireStra =(FireStrategy) clazz.getDeclaredConstructor().newInstance();

        }catch (Exception ex){
            ex.getStackTrace();
        }
    }


    private void fire() {

        //FireStrategy fireStra = new FireStrategyLAndR();
        fireStra.fire(x,y,dir);

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

    @Override
    public Rectangle getRect() {
        return rect;
    }
}
