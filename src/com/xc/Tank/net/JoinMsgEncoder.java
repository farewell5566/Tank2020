package com.xc.Tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class JoinMsgEncoder extends MessageToByteEncoder<JoinMsg> {


    @Override
    protected void encode(ChannelHandlerContext ctx, JoinMsg msg, ByteBuf byteBuf) throws Exception {

        byteBuf.writeInt(msg.toByte().length);
        byteBuf.writeBytes(msg.toByte());

    }


}
