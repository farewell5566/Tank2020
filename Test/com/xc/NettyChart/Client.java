package com.xc.NettyChart;



import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.io.IOException;


public class Client {
    private SocketChannel socketChan = null;


    public void Init(){
        EventLoopGroup worker = new NioEventLoopGroup(1);
        Bootstrap b = new Bootstrap();
        b.group(worker);
        b.channel(NioSocketChannel.class);
        b.handler(new MyHandler());

       //Bootstrap bs = new Bootstrap();
        try {
            ChannelFuture future = b.connect("localhost",8888).sync();
            System.out.println("connected to server");
            //等待关闭
            future.channel().closeFuture().sync();
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        worker.shutdownGracefully();

    }

    public void send(String text) {
        ByteBuf buf = Unpooled.copiedBuffer(text.getBytes());
        socketChan.pipeline().channel().writeAndFlush(buf);

    }

    private  class MyHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ClientHandler());
            socketChan = socketChannel;

        }

        private class ClientHandler extends ChannelInboundHandlerAdapter{
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                //网络上数据传输只能是0和1,将字节格式转换为String
                ByteBuf buf =(ByteBuf) msg;
                byte[]bytes = new byte[buf.readableBytes()];
                buf.getBytes(buf.readerIndex(),bytes);
                String str = new String(bytes);

                /*ByteBuf buf = (ByteBuf)msg;
                String str = buf.toString(CharsetUtil.UTF_8);*/
                System.out.println(str);

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
