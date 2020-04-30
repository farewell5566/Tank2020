package com.xc.Tank.net;


import com.xc.Tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.awt.*;


public class Client {

    public static Client INSTANCE = new Client();

    private SocketChannel socketChan = null;

    private Client(){}

    public void Init(){
        EventLoopGroup worker = new NioEventLoopGroup(1);
        Bootstrap b = new Bootstrap();
        b.group(worker);
        b.channel(NioSocketChannel.class);
        b.handler(new MyHandler());

        try {
            ChannelFuture future = b.connect("localhost",8888).sync();
            System.out.println("connected to server");
            //等待关闭
            future.channel().closeFuture().sync();
            System.out.println("end");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            worker.shutdownGracefully();

        }

        /*try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    public void send(JoinMsg msg) {

        socketChan.pipeline().channel().writeAndFlush(msg);
        //socketChan.writeAndFlush(new TankMsg(4,6));

    }

    private  class MyHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new JoinMsgEncoder());
            socketChannel.pipeline().addLast(new JoinMsgDecoder());
            socketChannel.pipeline().addLast(new ClientHandler());
            socketChan = socketChannel;

        }

        //private class ClientHandler extends ChannelInboundHandlerAdapter{
        private class ClientHandler extends SimpleChannelInboundHandler<JoinMsg>{
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, JoinMsg joinMsg) throws Exception {
                System.out.println(joinMsg);
                joinMsg.handle();

            }

/*            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                //网络上数据传输只能是0和1,将字节格式转换为String
                ByteBuf buf =(ByteBuf) msg;
                byte[]bytes = new byte[buf.readableBytes()];
                buf.getBytes(buf.readerIndex(),bytes);
                String str = new String(bytes);
                //ClientFrame.INSTANCE.Update(str);

                *//*ByteBuf buf = (ByteBuf)msg;
                String str = buf.toString(CharsetUtil.UTF_8);*//*
                System.out.println(str);

            }*/

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
               //netty中传输的数据都是bytebuf数据
                //ByteBuf buf = Unpooled.copiedBuffer(("client first connect").getBytes());
                //ctx.writeAndFlush(buf);
                //ctx.writeAndFlush(new TankMsg(4,6));
                ctx.writeAndFlush(new JoinMsg(TankFrame.INSTRANCE.getGm().getMyTank()));

            }
        }
    }
}
