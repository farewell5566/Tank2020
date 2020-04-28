package com.xc.nettycodec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public  void Init(){

        EventLoopGroup works = new NioEventLoopGroup();
        EventLoopGroup bosss = new NioEventLoopGroup();

        try {

            ServerBootstrap sbt = new ServerBootstrap();
            sbt.group(bosss, works);
            sbt.channel(NioServerSocketChannel.class);

            sbt.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new TankMsgDecoder());
                        socketChannel.pipeline().addLast(new ServerHandler());//这里可以加很多机器人
                }
            });
            ServerFrame.INSTANCE.serAreaUpdateMsg("服务启动");

            ChannelFuture future = sbt.bind(8888).sync();
            future.channel().closeFuture().sync();
        }catch(Exception ex)
        {
            ex.getStackTrace();
        }
        finally {
            bosss.shutdownGracefully();
            works.shutdownGracefully();

        }
        //sbt.childHandler(new MyChildHandler());
    }

    private  class ServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            TankMsg tankmsg = (TankMsg)msg ;
            ServerFrame.INSTANCE.serAreaUpdateMsg(tankmsg.toString());


            /*ByteBuf buf = (ByteBuf) msg;
            byte[]bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(),bytes);
            String str = new String(bytes);
            if (str.equals("__byebye__")){
                clients.remove(ctx.channel());
                ctx.close();
            }else
                clients.writeAndFlush(msg);
            System.out.println(str);*/

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

            cause.getStackTrace();
            try {
                System.out.println("捕捉到异常，要关闭");
               //clients.remove(ctx.channel());
                //ctx.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            clients.add(ctx.channel());
            System.out.println("连接上client");

        }
    }
}
