package toby.chap.mail.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

public interface MailSender {
	public void send(SimpleMailMessage mailMessage) throws MailException;
	public void send(SimpleMailMessage[] mailMessage) throws MailException;
}
