package com.xc.Tank.chainofresponsibility;

import com.xc.Tank.AbstractGameObject;
import com.xc.Tank.Tank;
import com.xc.Tank.Wall;

public class TankTankCollider implements Collider{
    @Override
    public boolean collide(AbstractGameObject go1,AbstractGameObject go2) {
        if (go1 instanceof Tank &&go2 instanceof Tank){
            Tank t1 = (Tank) go1;
            Tank t2 = (Tank) go2;
            if(t1.getRect().intersects(t2.getRect())){
                t1.back();
                t2.back();
                return true;
            }
            return true;
        }
        else if (go1 instanceof Tank && go2 instanceof Tank){
            collide(go2,go1);

        }
        return true;
    }
}
