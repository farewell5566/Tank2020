package com.xc.Tank;

import java.awt.*;
import java.io.Serializable;

public abstract class AbstractGameObject implements Serializable {
    public abstract void paint(Graphics g);
    public abstract boolean getLive();


    public abstract Rectangle getRect();
}
