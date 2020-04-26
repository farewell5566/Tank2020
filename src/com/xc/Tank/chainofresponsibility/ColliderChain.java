package com.xc.Tank.chainofresponsibility;

import com.xc.Tank.AbstractGameObject;
import com.xc.Tank.ConfigMgr;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider{
    List<Collider> colliders;

    public ColliderChain(){
        colliders= new ArrayList<>();
        initColliderChain();
    }

    private void initColliderChain() {
        String []clds = ((String)ConfigMgr.prop.get("AbstractObjectCollide")).split(",");
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            for (int i = 0;i<clds.length;++i){
                Class clazz =classLoader.loadClass("com.xc.Tank.chainofresponsibility." + clds[i]);
                Collider collider =(Collider) clazz.getDeclaredConstructor().newInstance();
                colliders.add(collider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        for (Collider collide : colliders){
            if(!collide.collide(go1,go2)){
                //break;
                return false;
            }
        }
        return true;
    }
}
