package com.coffeeshop.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Mohammad on 6/20/2017.
 */
public class GmailSMTP {

    private Session gmailSession;
    private String gmailAddress;

    public GmailSMTP(String userName,String password){
        gmailSession = createGmailSession(userName, password);
        gmailAddress=userName.concat("@gmail.com");
    }

    private Properties gmailConfig(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        return props;
    }

    private Session createGmailSession(String userName,String pass){
        Session session=Session.getDefaultInstance(gmailConfig(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName,pass);
                    }
                });
        return session;
    }

    public void sendMessage(String toAddress,String subject,StringBuilder text){
        Message message=new MimeMessage(gmailSession);
        try {
            message.setFrom(new InternetAddress(gmailAddress));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setText(text.toString());
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GmailSMTP gmailSMTP=new GmailSMTP("","");
        StringBuilder stringBuilder=new StringBuilder("");
        gmailSMTP.sendMessage("","",stringBuilder);
    }
}
