package com.xc.web.netty;



import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.IOException;


public class NettyClient {
    public static void main(String[]argus){
        EventLoopGroup worker = new NioEventLoopGroup(1);
        Bootstrap b = new Bootstrap();
        b.group(worker);
        b.channel(NioSocketChannel.class);
        b.handler(new MyHandler());

       //Bootstrap bs = new Bootstrap();
        try {
            ChannelFuture future = b.connect("localhost",8888).sync();
            //等待关闭
            future.channel().closeFuture().sync();
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        worker.shutdownGracefully();

    }

    private static class MyHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ClientHandler());

        }

        private class ClientHandler extends ChannelInboundHandlerAdapter{
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf buf = (ByteBuf)msg;
                String str = buf.toString(CharsetUtil.UTF_8);
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                super.exceptionCaught(ctx, cause);
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
               //netty中传输的数据都是bytebuf数据
                ByteBuf buf = Unpooled.copiedBuffer(("client first connect").getBytes());
                ctx.writeAndFlush(buf);

            }
        }
    }
}
