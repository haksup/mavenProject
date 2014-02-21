package toby.chap.mail.service.impl;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import toby.chap.mail.service.MailSender;

public class MockMailSender implements MailSender{

	@Override
	public void send(SimpleMailMessage mailMessage) throws MailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(SimpleMailMessage[] mailMessage) throws MailException {
		// TODO Auto-generated method stub
		
	}

}
