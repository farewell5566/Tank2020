package com.xc.Tank.net;

import com.xc.Tank.*;

import java.io.*;
import java.util.UUID;

public class JoinMsg extends Msg{
    private int x ,y;
    private Dir dir;
    private Group group;
    private UUID id;
    private boolean moving;
    private MsgType msgType;

    public JoinMsg(){
        this.msgType = MsgType.JoinMsg;
    }

    public JoinMsg(int x, int y , Dir dir, Group group, UUID id, boolean moving){
        this.x = x;
        this.y= y;
        this.dir = dir;
        this.group = group;
        this.id = id;
        this.moving = moving;
        this.msgType = MsgType.JoinMsg;

    };

    public JoinMsg(Player player){
        this.x = player.getX();
        this.y = player.getY();
        this.dir = player.getDir();
        this.group = player.getGroup();
        this.id = player.getId();
        this.moving = player.getMoing();
        this.msgType = MsgType.JoinMsg;

    }

    @Override
    public MsgType getMSgType() {
        return msgType;
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

    public Group getGroup() {
        return group;
    }

    public UUID getId() {
        return id;
    }

    public boolean isMoving() {
        return moving;
    }

    @Override
    public String toString() {
        return "JoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", id=" + id +
                ", moving=" + moving +
                '}';
    }

    public byte[] toByte(){
        byte[] b = null;
        ByteArrayOutputStream baos = null ;
        DataOutputStream dos = null;

        baos = new ByteArrayOutputStream();
        dos = new DataOutputStream(baos);

        try {
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            b = baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (dos != null) {
                    dos.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        return b;

    }

    public void parse(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bais);

        try {
            x = dis.readInt();
            y = dis.readInt();
            dir = Dir.values()[dis.readInt()];
            moving = dis.readBoolean();
            group = Group.values()[dis.readInt()];
            id = new UUID(dis.readLong(),dis.readLong());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bais!=null)
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (dis!=null)
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void handle() {
        if (this.id.equals(TankFrame.INSTRANCE.getGm().getMyTank().getId()))return;
        if (TankFrame.INSTRANCE.getGm().getTankByID(this.id)!=null)return ;
        Tank t = new Tank(this);
        TankFrame.INSTRANCE.getGm().add(t);
        Client.INSTANCE.send(new JoinMsg(TankFrame.INSTRANCE.getGm().getMyTank()));
        //send
    }
}
