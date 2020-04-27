package com.xc.web.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyServer {
    public static void  main(String[]argus){
        EventLoopGroup works = new NioEventLoopGroup();
        EventLoopGroup bosss = new NioEventLoopGroup();
        ServerBootstrap sbt = new ServerBootstrap();
        sbt.group(bosss,works);
        sbt.channel(NioServerSocketChannel.class);

        sbt.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new ClientHandler());//这里可以加很多机器人

            }
        });
        try {
            sbt.bind(8888).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //sbt.childHandler(new MyChildHandler());

    }

    private static class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            //System.out.println(msg.toString());

            ByteBuf buf = (ByteBuf) msg;
            System.out.println(buf.toString(CharsetUtil.UTF_8));
            ctx.writeAndFlush(msg);
            buf.release();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }
}
