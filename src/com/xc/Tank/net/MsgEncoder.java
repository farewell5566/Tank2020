package com.xc.Tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<Msg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf buf) throws Exception {
        //对于传递的信息，消息头和消息体，
        // 消息头包括 消息类型和消息体大小
        buf.writeInt(msg.getMSgType().ordinal());
        buf.writeInt(msg.toByte().length);
        buf.writeBytes(msg.toByte());
    }
}
