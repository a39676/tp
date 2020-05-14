package test.nettyDemo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {
	private final String host;
	private final int port;

	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap(); // 创建Bootstrap
			b.group(group) // 指定EventLoopGroup来处理客户端事件；需要EventLoopGroup的NIO实现
					.channel(NioSocketChannel.class) // 用于NIO传输的Channel类型
					.remoteAddress(new InetSocketAddress(host, port)) // 设置服务器的InetSocketAddress
					.handler(new ChannelInitializer<SocketChannel>() { // 当一个Channel创建时，把一个EchoClientHandler加入它的pipeline中
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new EchoClientHandler());
						}
					});
			ChannelFuture f = b.connect().sync(); // 连接到远端，一直等到连接完成
			f.channel().closeFuture().sync(); // 一直阻塞到Channel关闭
		} finally {
			group.shutdownGracefully().sync(); // 关闭所有连接池，释放所有资源
		}

	}

	public static void main(String args[]) throws Exception {
		new EchoClient("localhost", 12000).start();
	}

}
