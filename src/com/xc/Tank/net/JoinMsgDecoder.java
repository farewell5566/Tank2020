package com.xc.Tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.util.List;

public class JoinMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        if (buf.readableBytes()<37)return;
        int len = buf.readInt();
        byte[]bytes = new byte[len];

        buf.readBytes(bytes);
        JoinMsg msg = new JoinMsg();
        msg.parse(bytes);
        list.add(msg);

    }
}
