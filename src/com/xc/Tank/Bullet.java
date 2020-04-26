package com.xc.Tank;

import java.awt.*;

public class Bullet extends AbstractGameObject {

    private int speed ;
    private int x,y;
    private Dir dir;
    private Group group;
    private static int width,height;
    private Boolean isLive = true;
    private Rectangle rect = null;

    public Bullet (){

    }

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
        speed =  Integer.parseInt((String)ConfigMgr.prop.get("BULLET_SPEED"));
        rect = new Rectangle(x,y,this.width,this.height);
    }

    public void move() {
        switch(dir){
            case U:
                y -= speed;
                break;
            case D:
                y += speed;
                break;
            case R:
                x += speed;
                break;
            case L:
                x -= speed;
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
/*        Color old = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(rect.x,rect.y,rect.width,rect.height);
        g.setColor(old);*/

        rect.x = this.x;
        rect.y = this.y;


        isOutBound();
    }

    public  void isOutBound() {
        if (x<0||y<0||x>TankFrame.INSTRANCE.getGameWidth() ||y>TankFrame.INSTRANCE.getGameHeight())
            this.isLive = false;
        else
           this.isLive = true;
    }

/*    public boolean collideWith(Tank tank){


    }*/

    public Rectangle getRect(){
        return this.rect;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
