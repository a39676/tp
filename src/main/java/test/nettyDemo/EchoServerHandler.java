package test.nettyDemo;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {// 每次收到消息时被调用
		ByteBuf in = (ByteBuf) msg;
		String str = in.toString(StandardCharsets.UTF_8);
		System.out.println("Server received: " + str); // 把消息打印到控制台
		in.writeBytes((str + " received").getBytes(StandardCharsets.UTF_8));
		ctx.write(in); // 将收到的消息写入发送方，不刷新输出消息
	}

	@Override // 用来通知handler上一个ChannelRead()是被这批消息中的最后一个消息调用
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// 刷新挂起的数据到远端，然后关闭Channel
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}

	@Override // 在读操作异常被抛出时被调用
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace(); // 打印异常堆栈跟踪消息
		ctx.close(); // 关闭这个Channel
	}

}
