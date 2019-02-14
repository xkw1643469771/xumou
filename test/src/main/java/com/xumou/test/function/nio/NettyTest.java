package com.xumou.test.function.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 *
 */
public class NettyTest {

    ExecutorService es = Executors.newFixedThreadPool(10);
    long count;
    // 简单的服务器读取
    @Test
    public void server() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            try{
                                ByteBuf buf = (ByteBuf) msg;
                                System.out.println(msg);
                                ByteBuf buffer = ctx.alloc().buffer();
                                buffer.writeBytes("HTTP/1.1 200 OK\nContent-Type: application/json;charset=UTF-8\n\n123123".getBytes());
                                ctx.writeAndFlush(buffer);
                                ctx.close();
                            }finally {
                                ReferenceCountUtil.release(msg);
                            }
                        }
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            System.err.println(cause.getMessage());
                        }
                    });
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.bind(8888).sync().channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    // 简单的客户端
    @Test
    public void client() throws InterruptedException {
        EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(nioEventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ByteBuf buffer = ctx.alloc().buffer();
                            buffer.writeBytes("123123123123".getBytes());
                            ctx.writeAndFlush(buffer);
                        }
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf buf = (ByteBuf) msg;
                            System.out.println(buf.toString(CharsetUtil.US_ASCII));
                            ctx.close();
                        }
                    });
                }
            });
            bootstrap.connect("localhost",8888).sync()
                    .channel().closeFuture().sync();
        }finally {
            nioEventLoopGroup.shutdownGracefully();
        }
    }

    // 服务器_字节转换
    @Test
    public void byteTrans() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ByteToMessageDecoder() {
                        protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
                            if(byteBuf.readableBytes() >= 4)
                                list.add(byteBuf.readBytes(4));
                        }
                    }, new SimpleChannelInboundHandler<ByteBuf>(){
                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                            System.out.println(byteBuf.readInt());
                        }
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            System.err.println(cause.getMessage());
                        }
                    });
                }
            });
            serverBootstrap.bind(8888).sync().channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    // 服务器_读取一行字符串
    @Test
    public void trans() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ByteToMessageDecoder() {
                        protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
                            StringBuilder sb = new StringBuilder();
                            while(byteBuf.readableBytes() > 0){
                                char c = (char)byteBuf.readByte();
                                if(c == '\n') break;
                                sb.append(c);
                            }
                            if(sb.toString().endsWith("\r"))
                                sb.delete(sb.length() - 1,sb.length());
                            list.add(sb.toString());
                        }
                    }, new SimpleChannelInboundHandler<String>() {
                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String str) throws Exception {
                            System.out.println(str);
                        }
                    });
                }
            });
            serverBootstrap.bind(8888).sync().channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    // 服务器_HTTP
    @Test
    public void http() throws InterruptedException {
        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boos, worker);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ReadTimeoutHandler( 10));//读超时
                    socketChannel.pipeline().addLast(new WriteTimeoutHandler(10));//写超时
                    socketChannel.pipeline().addLast(new HttpServerCodec());//转为http
                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            if(msg instanceof HttpRequest){
                                HttpRequest request = (HttpRequest) msg;
                                System.out.println(request.uri());
                                String html = "<h1>"+(Thread.currentThread().getName())+" : "+(count++)+"</h1>";
                                ByteBuf content = Unpooled.wrappedBuffer(html.getBytes());
                                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, content);
                                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
                            }
                        }
                    });
                }
            });
            serverBootstrap.bind(8888).sync().channel().closeFuture().sync();
        }finally {
            boos.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    //==================================================================================================================

    public static void sleep(long millis){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}