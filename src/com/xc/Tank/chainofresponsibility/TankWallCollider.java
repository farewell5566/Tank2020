package com.xc.Tank.chainofresponsibility;

import com.xc.Tank.AbstractGameObject;
import com.xc.Tank.Bullet;
import com.xc.Tank.Tank;
import com.xc.Tank.Wall;

public class TankWallCollider implements Collider{
    @Override
    public boolean collide(AbstractGameObject go1,AbstractGameObject go2) {
        if (go1 instanceof Tank &&go2 instanceof Wall){
            Tank t = (Tank) go1;
            Wall wall = (Wall) go2;
            //System.out.println(t.toString());

            if(t.getRect().intersects(wall.getRect())){
                //System.out.println(t.toString());
                t.back();
            }
        }
        else if (go1 instanceof Wall && go2 instanceof Tank){
            collide(go2,go1);

        }
        return true;
    }
}
