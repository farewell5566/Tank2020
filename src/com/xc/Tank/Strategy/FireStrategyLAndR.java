package com.xc.Tank.Strategy;

import com.xc.Tank.*;

public class FireStrategyLAndR implements FireStrategy{
    @Override
    public void fire(int x,int y,Dir dir){
        Bullet bulletL,bulletR ;

        bulletL =new Bullet(x + (ResourceMgr.goodTankU.getWidth()  - ResourceMgr.bulletU.getWidth())/2,y + (ResourceMgr.goodTankU.getHeight()- ResourceMgr.bulletU.getHeight())/2,Dir.L,Group.GOOD);
        bulletR =new Bullet(x + (ResourceMgr.goodTankU.getWidth()  - ResourceMgr.bulletU.getWidth())/2,y + (ResourceMgr.goodTankU.getHeight()- ResourceMgr.bulletU.getHeight())/2,Dir.R,Group.GOOD);
        TankFrame.INSTRANCE.getGm().addBullet(bulletL);
        TankFrame.INSTRANCE.getGm().addBullet(bulletR);
    }

}
