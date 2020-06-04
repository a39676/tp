package test;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.io.CharStreams;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SshTest {

	private String host = "";
	private int port = 22;
	private String username = "";
	private String pwd = "";
	
	
	public static void main(String[] args) {
		SshTest s = new SshTest();
		s.testing();
	}
	
	public void testing() {
		Session session = null;
		Channel channel = null;

		try {

			JSch jsch = new JSch();
			
			session = jsch.getSession(username, host, port);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "accept-new");
			session.setConfig(config);
			session.setPassword(pwd);
			session.connect();

			System.out.println("t");
			String command = "date";
			channel = session.openChannel("exec");
			ChannelExec chanelExec = (ChannelExec) channel;
			chanelExec.setCommand(command);
			
			chanelExec.setInputStream(null);
            InputStream output = chanelExec.getInputStream();
            chanelExec.connect();
            

            String result = CharStreams.toString(new InputStreamReader(output));
            System.out.println(result);

			channel.disconnect();
			session.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			channel.disconnect();
			session.disconnect();
		}
	}

}
