package com.natint.email;

import com.natint.data.Data;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by skn on 12/10/2015.
 */
public class JavaxMail extends Email {

    private String emailProtocol;
    private String metaEmail;
    private String metaPassword;

    public JavaxMail(String emailProtocol, String metaEmail, String metaPassword) {
        this.emailProtocol = emailProtocol;
        this.metaEmail = metaEmail;
        this.metaPassword = metaPassword;
    }

    @Override
    public List<Data> collectData() {
        try {
            Folder folder = establishConnection();
            Message[] messages = folder.getMessages();

            for (Message message : messages) {
                String contentType = message.getContentType();
                if (contentType.contains("multipart")) {
                    Multipart multipart = (Multipart) message.getContent();
                    for (int i = 0; i < multipart.getCount(); i++) {
                        MimeBodyPart bodyPart = (MimeBodyPart) multipart.getBodyPart(i);
                        System.out.println("Body part content " + bodyPart.getContent());
                    }
                }
            }
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Folder establishConnection() throws MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "pop3");

        Session session = Session.getDefaultInstance(properties);

        Store store = session.getStore();
        store.connect(emailProtocol, metaEmail, metaPassword);

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        return folder;
    }
}
