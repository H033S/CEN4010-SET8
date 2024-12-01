package fiu.cen.menug.service;

import fiu.cen.menug.model.EmailDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleEmail(EmailDetails emailDetails){

        try {
            LOG.info("Creating email for {}",emailDetails.getRecipient());
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMsgBody());
            mailMessage.setSubject(emailDetails.getSubject());

            LOG.info("Sending email");
            this.javaMailSender.send(mailMessage);
        }
        catch (Exception e) {

            LOG.info("Something went wrong while generating email. Exception:{}\nMessage:{}",
                    e.getCause().toString(),
                    e.getMessage()
            );
        }
    }
}
