package com.xc.Tank.net;

public abstract class Msg {
    public  Msg(){};
    abstract public byte[] toByte();
    abstract public void parse(byte[]bytes);
    abstract public void handle();
    abstract public MsgType getMSgType();
}
