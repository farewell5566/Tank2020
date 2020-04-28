package com.xc.nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TankMsgTest {
    @Test
    void decode(){
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new TankMsgDecoder());
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(4);
        buf.writeInt(3);
        ch.writeInbound(buf);
        TankMsg msg = (TankMsg)ch.readInbound();
        assertEquals(msg.x,4);
        assertEquals(msg.y,3);



    }

    @Test
    void encode(){
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new TankMsgEncoder());
        ch.writeOutbound(new TankMsg(3,4));
        ByteBuf buf = ch.readOutbound();
        int x = buf.readInt();
        int y = buf.readInt();
        assertEquals(x,3);
        assertEquals(y,4);


    }

}