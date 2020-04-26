package com.xc.Tank.chainofresponsibility;

import com.xc.Tank.AbstractGameObject;

import java.io.Serializable;

public interface Collider extends Serializable {
    //如果向下传递检测碰撞的话，返回ture，停止检测为false
    boolean collide(AbstractGameObject go1,AbstractGameObject go2);
}
