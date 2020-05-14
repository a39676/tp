package test.nettyDemo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
private final int port;
	
	public EchoServer(int port){
		this.port = port;
	}
	
	public void start() throws Exception{
		final EchoServerHandler serverHandler = new EchoServerHandler();
		EventLoopGroup group = new NioEventLoopGroup();	//创建EventLoopGroup
		try{
			ServerBootstrap b = new ServerBootstrap();	//创建ServerBootstrap
			b.group(group)
			.channel(NioServerSocketChannel.class)		//指定使用一个NIO传输Channel
			.localAddress(new InetSocketAddress(port))	//用指定的端口设置socket地址
			.childHandler(new ChannelInitializer<SocketChannel>() {	//在Channel的ChannelPipeline中加入EchoServerHandler
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(serverHandler);//EchoServerHandler是@Sharable的，所以我们可以一直用同一个实例
				}
			});
			ChannelFuture f = b.bind().sync();//异步的绑定服务器，sync()一直等到绑定完成
			f.channel().closeFuture().sync();//获得这个Channel的CloseFuture，阻塞当前线程直到关闭操作完成
		}finally{
			group.shutdownGracefully().sync();//关闭EventLoopGroup，释放所有资源
		}
		
	}
 
	public static void main(String args[]) throws Exception {
		new EchoServer(12000).start();
	}
}
