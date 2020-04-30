package com.xc.Tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes()<8) return;

        byteBuf.markReaderIndex();
        MsgType msgType = MsgType.values()[byteBuf.readInt()];
        int len = byteBuf.readInt();
        if (len>byteBuf.readableBytes()){
            byteBuf.resetReaderIndex();
            return;
        }

        byte[]bytes = new byte[len];

        byteBuf.readBytes(bytes);

        Msg msg =(Msg) Class.forName("com.xc.Tank.net." + msgType).getDeclaredConstructor().newInstance() ;
        msg.parse(bytes);
        list.add(msg);
    }
}
