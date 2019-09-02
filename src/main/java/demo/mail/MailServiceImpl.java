package demo.mail;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;

import dateHandle.DateUtilCustom;
import emailHandle.MailHandle;

public class MailServiceImpl {
	
	private static String mailName = "491808878@qq.com";
	private static String mailPwd = "fjbihjqvurgubjji";
	

	public static void main(String[] args) {
		MailServiceImpl t = new MailServiceImpl();
		Properties prop = MailTool.getProperties("tencentMailSsl.properties");
		MailProperties p = MailTool.buildMailProperties(prop);
		System.out.println(p);
		
		try {
			t.searchInbox();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Message[] searchInbox() throws MessagingException, IOException {
		MailHandle mailHandle = new MailHandle();

		Properties imapProperties = null;
		Properties smtpProperties = null;
		try {
			imapProperties = MailTool.getProperties("tencentImapSsl.properties");
			smtpProperties = MailTool.getProperties("tencentSmtpSsl.properties");
		} catch (Exception e) {
			e.printStackTrace();
			return new Message[] {};
		}
		
		Store store = mailHandle.getMailStore(
				mailName, 
				mailPwd, 
				smtpProperties, 
				imapProperties
				);
		
		Folder inbox = mailHandle.getInbox(store);
		
		SearchTerm searchTerm = new SearchTerm() {
			private static final long serialVersionUID = 7873209385471356176L;

			@Override
			public boolean match(Message message) {
				Date receiveDate = null;
				try {
					receiveDate = message.getReceivedDate();
				} catch (MessagingException e1) {
					e1.printStackTrace();
					return false;
				}
				if(receiveDate.before(DateUtilCustom.localDateTimeToDate(LocalDateTime.now().minusDays(50)))) {
					return false;
				}
				
				Address[] from = null;
				try {
					from = message.getFrom();
				} catch (MessagingException e) {
					e.printStackTrace();
					return false;
				}
				if(from == null || from.length < 1) {
					return false;
				}
				boolean flag = false;
				for(Address f : from) {
					System.out.println(f);
//					Pattern pattern = Pattern.compile("(^@189.cn)");
//				    Matcher matcher = pattern.matcher(f.toString());
//				    if (matcher.find()) {
//				    	if(matcher.group(1).equals("@189.cn")) {
//				    		flag = true;
//				    	}
//				    } 
//					if(f.toString().equals("@189.cn")) {
//						flag = true;
//					}
					flag = true;
				}
				if(!flag) {
					return false;
				}
				
				try {
					MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
					String content = mimeMultipart.getBodyPart(0).getContent().toString();
					System.out.println(content);
					if(content.contains("")) {
						return true;
					}
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		};
		
		Message[] targetMail = mailHandle.getMailReadOnly(inbox, searchTerm);
		
		System.out.println(targetMail[0].getSubject());
		
		return targetMail;
	}
	
	public SearchTerm singleSearchTerm(String targetSendFrom, String targetContent, Date startDate) {
		SearchTerm searchTerm = new SearchTerm() {
			private static final long serialVersionUID = 7873209385471356176L;
			
			public boolean match(Message message) {
				Date receiveDate = null;
				try {
					receiveDate = message.getReceivedDate();
				} catch (MessagingException e1) {
					e1.printStackTrace();
					return false;
				}
				if(receiveDate.before(startDate)) {
					return false;
				}
				
				Address[] from = null;
				try {
					from = message.getFrom();
				} catch (MessagingException e) {
					e.printStackTrace();
					return false;
				}
				if(from == null || from.length < 1) {
					return false;
				}
				boolean flag = false;
				for(Address f : from) {
					if(f.toString().equals(targetSendFrom)) {
						flag = true;
					}
				}
				if(!flag) {
					return false;
				}
				
				try {
					MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
					String content = mimeMultipart.getBodyPart(0).getContent().toString();
					if(content.contains(targetContent)) {
						return true;
					}
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		};
		return searchTerm;
	}
	
}
