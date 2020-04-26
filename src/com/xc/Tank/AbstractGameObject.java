package com.xc.Tank;

import java.awt.*;

public abstract class AbstractGameObject {
    public abstract void paint(Graphics g);
    public abstract boolean getLive();


    public abstract Rectangle getRect();
}
