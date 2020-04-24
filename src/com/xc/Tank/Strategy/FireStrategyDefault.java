package com.xc.Tank.Strategy;

import com.xc.Tank.*;

public class FireStrategyDefault implements FireStrategy{


    @Override
    public void fire(int x,int y,Dir dir) {
        Bullet bullet;
        if (dir == Dir.U || dir == Dir.D){
            bullet = new Bullet(x + (ResourceMgr.goodTankU.getWidth() - ResourceMgr.bulletU.getWidth())/2,y + (ResourceMgr.goodTankU.getHeight() - ResourceMgr.bulletU.getHeight())/2,dir, Group.GOOD);
        }
        else {
            bullet = new Bullet(x + (ResourceMgr.goodTankU.getWidth() - ResourceMgr.bulletU.getWidth())/2,y + (ResourceMgr.goodTankU.getHeight() - ResourceMgr.bulletU.getHeight())/2, dir, Group.GOOD);
        }

        TankFrame.INSTRANCE.addBullet(bullet);

    }
}
