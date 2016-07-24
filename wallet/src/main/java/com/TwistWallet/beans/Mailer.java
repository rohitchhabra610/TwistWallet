package com.TwistWallet.beans;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.TwistWallet.dto.MailUser;

@Component
public class Mailer {

	@Autowired
    private JavaMailSender mailSender;
	
    @Autowired
    private VelocityEngine velocityEngine;
    
	@Autowired
	private MailConfig mailConfig;
	 
	 public void sendMail(MailUser user) {	 
		 
		  MimeMessagePreparator preparator = new MimeMessagePreparator() {

	            public void prepare(MimeMessage mimeMessage)
	                throws Exception
	            {
	                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	                message.setFrom(mailConfig.getFromMailId());
	                message.setTo(user.getEmailAddress());
	                if(user.getSubject() != null)
	                    message.setSubject(user.getSubject());
	                if(user.getCcMailList() != null)
	                    message.setCc(user.getCcMailList());
	                else
	                if(user.getCcMailId() != null)
	                    message.setCc(user.getCcMailId());
	                if(user.getBccMailList() != null)
	                    message.setBcc(user.getBccMailList());
	                else
	                if(user.getBccMailId() != null && !"false".equalsIgnoreCase(user.getBccMailId()))
	                    message.setBcc(user.getBccMailId());
	                Map model = new HashMap();
	                model.put("user", user);
	                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, user.getTemplateName(), "UTF-8", model);
	                message.setText(text, true);
	            }
   
		  }  ;
	        mailSender.send(preparator);
		  
	 }
	}

