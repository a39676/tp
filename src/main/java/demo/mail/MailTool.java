package demo.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailTool {
	
	public static String imapAuth = "imap.auth";
	public static String imapHost = "imap.host";
	public static String imapPort = "imap.port";
	public static String imapStarttlsEnable = "imap.starttls.enable";
	public static String imapSocketFactoryClass = "imap.socketFactory.class";
	public static String smtpAuth = "smtp.auth";
	public static String smtpHost = "smtp.host";
	public static String smtpPort = "smtp.port";
	public static String smtpStarttlsEnable = "smtp.starttls.enable";
	public static String smtpSocketFactoryClass = "smtp.socketFactory.class";
	
	public static MailProperties buildMailProperties(Properties prop) {
		
		MailProperties p = new MailProperties();
		p.setImapAuth(prop.getProperty(imapAuth));
		p.setImapHost(prop.getProperty(imapHost));
		p.setImapPort(prop.getProperty(imapPort));
		p.setImapStarttlsEnable(prop.getProperty(imapStarttlsEnable));
		p.setImapSocketFactoryClass(prop.getProperty(imapSocketFactoryClass));
		p.setSmtpAuth(prop.getProperty(smtpAuth));
		p.setSmtpHost(prop.getProperty(smtpHost));
		p.setSmtpPort(prop.getProperty(smtpPort));
		p.setSmtpStarttlsEnable(prop.getProperty(smtpStarttlsEnable));
		p.setSmtpSocketFactoryClass(prop.getProperty(smtpSocketFactoryClass));
		
		return p;
	}
	
	public static Properties getProperties(String propertiesFileName) {
		Properties prop = new Properties();
		try (InputStream input = MailServiceImpl.class.getClassLoader().getResourceAsStream("properties/mail/" + propertiesFileName)) {

            if (input == null) {
                System.out.println("Sorry, unable to find " + propertiesFileName);
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            System.out.println(prop);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		return prop;
	}

}
