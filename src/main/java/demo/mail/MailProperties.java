package demo.mail;

public class MailProperties {

	private String imapAuth;
	private String imapHost;
	private String imapPort;
	private String imapStarttlsEnable;
	private String imapSocketFactoryClass;
	private String smtpAuth;
	private String smtpHost;
	private String smtpPort;
	private String smtpStarttlsEnable;
	private String smtpSocketFactoryClass;
	
	
	public String getImapAuth() {
		return imapAuth;
	}
	public void setImapAuth(String imapAuth) {
		this.imapAuth = imapAuth;
	}
	public String getImapHost() {
		return imapHost;
	}
	public void setImapHost(String imapHost) {
		this.imapHost = imapHost;
	}
	public String getImapPort() {
		return imapPort;
	}
	public void setImapPort(String imapPort) {
		this.imapPort = imapPort;
	}
	public String getImapStarttlsEnable() {
		return imapStarttlsEnable;
	}
	public void setImapStarttlsEnable(String imapStarttlsEnable) {
		this.imapStarttlsEnable = imapStarttlsEnable;
	}
	public String getImapSocketFactoryClass() {
		return imapSocketFactoryClass;
	}
	public void setImapSocketFactoryClass(String imapSocketFactoryClass) {
		this.imapSocketFactoryClass = imapSocketFactoryClass;
	}
	public String getSmtpAuth() {
		return smtpAuth;
	}
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getSmtpStarttlsEnable() {
		return smtpStarttlsEnable;
	}
	public void setSmtpStarttlsEnable(String smtpStarttlsEnable) {
		this.smtpStarttlsEnable = smtpStarttlsEnable;
	}
	public String getSmtpSocketFactoryClass() {
		return smtpSocketFactoryClass;
	}
	public void setSmtpSocketFactoryClass(String smtpSocketFactoryClass) {
		this.smtpSocketFactoryClass = smtpSocketFactoryClass;
	}
	@Override
	public String toString() {
		return "MailProperties [imapAuth=" + imapAuth + ", imapHost=" + imapHost + ", imapPort=" + imapPort
				+ ", imapStarttlsEnable=" + imapStarttlsEnable + ", imapSocketFactoryClass=" + imapSocketFactoryClass
				+ ", smtpAuth=" + smtpAuth + ", smtpHost=" + smtpHost + ", smtpPort=" + smtpPort
				+ ", smtpStarttlsEnable=" + smtpStarttlsEnable + ", smtpSocketFactoryClass=" + smtpSocketFactoryClass
				+ "]";
	}

	

}
