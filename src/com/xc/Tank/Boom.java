package com.xc.Tank;

import java.awt.*;

public class Boom extends AbstractGameObject{
    private int x;
    private int y;
    private boolean isLive;
    private int imageIndex = 0;
    private static final int imageEpdNum= ResourceMgr.explodes.length;
    private Rectangle rect;

    public Boom(int x,int y,boolean isLive){
        this.x = x;
        this.y = y;
        this.isLive = isLive;
        //这里没设置rect左右
        rect = null;
    }


    public void paint(Graphics g) {
        if (isLive == true) {
            g.drawImage(ResourceMgr.explodes[imageIndex], this.x, this.y, null);
            ++imageIndex;
            if (imageIndex == imageEpdNum) {
                imageIndex = 0;
                this.isLive = false;
            }
        }
    }

    public boolean getLive(){
        return isLive;
    }

    public Rectangle getRect(){
        return rect;
    }


}
