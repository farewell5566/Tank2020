package com.xc.Tank.chainofresponsibility;

import com.xc.Tank.AbstractGameObject;
import com.xc.Tank.Bullet;
import com.xc.Tank.Tank;
import com.xc.Tank.Wall;

public class BulletWallCollider implements Collider{
    @Override
    public boolean collide(AbstractGameObject go1,AbstractGameObject go2) {
        if (go1 instanceof Bullet &&go2 instanceof Wall){
            Bullet bullet = (Bullet) go1;
            Wall wall = (Wall) go2;
            if(bullet.getRect().intersects(wall.getRect())){
                bullet.setLive(false);
                return false;
            }
        }
        else if (go1 instanceof Wall && go2 instanceof Bullet){
            collide(go2,go1);

        }
        return true;
    }
}
