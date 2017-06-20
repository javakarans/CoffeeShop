package com.coffeeshop.services;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public void sendMessage(String toAddress, String subject, StringBuilder text,List<File> attachmentList){
        Message message=new MimeMessage(gmailSession);
        BodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        try {
            message.setFrom(new InternetAddress(gmailAddress));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));
            message.setSubject(subject);
            messageBodyPart.setText(text.toString());
            multipart.addBodyPart(messageBodyPart);
            message.setText(text.toString());
            if(!attachmentList.isEmpty()){
                Iterator<File> iterator = attachmentList.iterator();
                while (iterator.hasNext()){
                    File attachment = iterator.next();
                    messageBodyPart=new MimeBodyPart();
                    DataSource dataSource=new FileDataSource(attachment);
                    messageBodyPart.setDataHandler(new DataHandler(dataSource));
                    messageBodyPart.setFileName(attachment.getName());
                    multipart.addBodyPart(messageBodyPart);
                }
            }
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GmailSMTP gmailSMTP=new GmailSMTP("nutellapluserbil","nutellaplus0000");
        StringBuilder stringBuilder=new StringBuilder("Nutella Plus Test Gmail");
        List<File> files=new ArrayList<>();
        gmailSMTP.sendMessage("mmirzakhani1993@gmail.com","Test4",stringBuilder,files);
    }
}
