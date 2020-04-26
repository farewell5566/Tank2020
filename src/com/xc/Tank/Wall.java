package com.xc.Tank;

import java.awt.*;

public class Wall extends AbstractGameObject{
    private int x,y,height,width;
    private Rectangle rect =null;
    public Wall(){
        this.x = Integer.parseInt((String)ConfigMgr.prop.get("WALLX"));
        this.y = Integer.parseInt((String)ConfigMgr.prop.get("WALLY"));
        this.height = Integer.parseInt((String)ConfigMgr.prop.get("WALLHEIGHT"));
        this.width = Integer.parseInt((String)ConfigMgr.prop.get("WALLWIDTH"));
        rect = new Rectangle(x,y,width,height);
    }
    @Override
    public void paint(Graphics g) {
        Color old = g.getColor();
        g.setColor(Color.white);
        g.fillRect(x,y,width,height);
        g.setColor(old);
    }

    @Override
    public boolean getLive() {
        return true;
    }

    @Override
    public Rectangle getRect() {
        return this.rect;
    }
}
