package com.xc.Tank.Strategy;

import com.xc.Tank.Dir;

import java.io.Serializable;

public interface FireStrategy extends Serializable {

     void fire(int x,int y,Dir dir);

}
