package com.xc.Tank;

import java.util.Random;

public enum Dir {
    L,R,U,D;

    static Random random = new Random();

    static public Dir getRandom() {
        return Dir.values()[random.nextInt(Dir.values().length)];
    }
}
