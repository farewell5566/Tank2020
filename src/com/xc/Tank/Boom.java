package com.xc.Tank;

import java.awt.*;

public class Boom {
    private int x;
    private int y;
    private boolean isLive;
    private int imageIndex = 0;
    private static final int imageEpdNum= ResourceMgr.explodes.length;

    public Boom(int x,int y,boolean isLive){
        this.x = x;
        this.y = y;
        this.isLive = isLive;
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

}
