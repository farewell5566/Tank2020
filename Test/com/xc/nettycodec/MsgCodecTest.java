package com.xc.nettycodec;



import com.xc.Tank.Dir;
import com.xc.Tank.Group;
import com.xc.Tank.Player;
import com.xc.Tank.net.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MsgCodecTest {
    @Test
    void encode() {
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new MsgEncoder());
        Player player = new Player(10,5,Dir.U,Group.GOOD);
        UUID idTest = player.getId();

        StartMovingMsg mvMsg = new StartMovingMsg(player);

        com.xc.Tank.net.JoinMsg msg = new JoinMsg(player);

        ch.writeOutbound(msg);

        ByteBuf buf = ch.readOutbound();
        //assertEquals(1,buf.readInt());
        switch(buf.readInt())
        {
            case 0: //JoinMsg
                assertEquals(33,buf.readInt());
                assertEquals(10,buf.readInt());
                assertEquals(5,buf.readInt());
                assertEquals(Dir.U, Dir.values()[buf.readInt()]);
                assertEquals(false,buf.readBoolean());
                assertEquals(Group.GOOD,Group.values()[buf.readInt()]);
                break;
            case 1: //StartMovingMsg
                assertEquals(28,buf.readInt());
                assertEquals(10,buf.readInt());
                assertEquals(5,buf.readInt());
                assertEquals(Dir.U,Dir.values()[buf.readInt()]);
                UUID idTemp = new UUID(buf.readLong(),buf.readLong());
                assertEquals(idTest,idTemp);
        }
    }

    @Test
    void decode() {
        EmbeddedChannel chan = new EmbeddedChannel();
        chan.pipeline().addLast(new MsgDecoder());

        Player player = new Player(5,6, Dir.U, Group.GOOD);
        JoinMsg msg = new JoinMsg(player);
        StartMovingMsg mvmsg = new StartMovingMsg(player);

        byte[]bytes = mvmsg.toByte();
        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(1);
        buf.writeInt(28);
        buf.writeBytes(bytes);

        chan.writeInbound(buf);
        StartMovingMsg msg1 = (StartMovingMsg) chan.readInbound();

        assertEquals(5,msg1.getX());
        assertEquals(6,msg1.getY());
        assertEquals(Dir.U,msg1.getDir());
        //assertEquals(Group.GOOD,msg1.getGroup());




    }





}