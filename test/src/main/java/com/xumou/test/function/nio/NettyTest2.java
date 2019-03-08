package com.xumou.test.function.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.Test;

/**
 *
 */
public class NettyTest2 {

    long count = 1;

    @Test
    public void test1(){
        EventLoopGroup boos  = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boos,worker);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf buffer = ctx.alloc().buffer();
                            String str = "HTTP/1.1 200 OK\nContent-Type:text/html;charset=UTF-8\n\n";
                            str += Thread.currentThread().getName() + "\t";
                            str += count++;
                            buffer.writeBytes(str.getBytes());
                            ctx.writeAndFlush(buffer);
                            Thread.sleep(10000);
                        }
                    });
                }
            });
            serverBootstrap.bind(7676).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boos.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    @Test
    public void t(){
        System.out.println(count = count << 1);
        System.out.println(count = count << 1);
        System.out.println(count = count << 1);
        System.out.println(count = count << 1);
        System.out.println(count = count << 1);
        System.out.println(count = count << 1);
    }

}