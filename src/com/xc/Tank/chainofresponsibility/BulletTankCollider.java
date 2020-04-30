package com.xc.Tank.chainofresponsibility;

import com.xc.Tank.*;

import java.awt.*;

public class BulletTankCollider implements Collider{
    @Override
    public boolean collide(AbstractGameObject go1,AbstractGameObject go2) {
        if (go1 instanceof Tank &&go2 instanceof Bullet){
            Tank tank = (Tank)go1;
            Bullet bullet = (Bullet)go2;

            if (!tank.getLive()||!bullet.getLive()) return false;
            if (tank.GetGroup() == bullet.getGroup()) return true;

            if (bullet.getRect().intersects(tank.getRect())){
                TankFrame.INSTRANCE.getGm().add(new Boom(tank.getX(),tank.getY(),true));
                bullet.setLive(false);
                tank.setLive(false);
                return false;
            }
            else
                return true;
        }
        else if (go1 instanceof Bullet && go2 instanceof Tank){
            collide(go2,go1);

        }
        return true;
    }
}
