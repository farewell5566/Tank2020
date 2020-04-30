package com.xc.Tank.net;

import com.xc.Tank.Dir;
import com.xc.Tank.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class StartMovingMsg extends Msg{
    private int x,y;
    private Dir dir;
    private UUID id;
    private MsgType msgType;
/*    public StartMovingMsg (){
        msgType = MsgType.StartMovingMsg;
    }*/

    public StartMovingMsg(Player player){
        this.x = player.getX();
        this.y = player.getY();
        this.dir = player.getDir();
        this.id = player.getId();
        msgType = MsgType.StartMovingMsg;
    }

    @Override
    public byte[] toByte() {
        byte[]bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try{
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();
        }
        catch(IOException e){
            e.getStackTrace();
        }
        finally {
            if (dos!=null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public UUID getId() {
        return id;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    @Override
    public void parse(byte[] bytes) {




    }

    @Override
    public void handle() {

    }

    @Override
    public MsgType getMSgType() {
        return this.msgType;
    }
}
