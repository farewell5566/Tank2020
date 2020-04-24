package com.xc.Tank.Strategy;

import com.xc.Tank.*;

public class FireStrategyAllDir implements FireStrategy{
    @Override
    public void fire(int x,int y,Dir dir){
        Bullet bullet ;
        for (Dir d:Dir.values()){
            bullet =new Bullet(x + (ResourceMgr.goodTankU.getWidth()  - ResourceMgr.bulletU.getWidth())/2,y + (ResourceMgr.goodTankU.getHeight()- ResourceMgr.bulletU.getHeight())/2,d,Group.GOOD);
            TankFrame.INSTRANCE.addBullet(bullet);
        }
    }

}
