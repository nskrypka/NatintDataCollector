package com.natint.email;

import com.natint.data.Data;
import com.sun.mail.imap.IMAPFolder;
import org.apache.commons.lang.StringUtils;

import javax.mail.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by skn on 12/10/2015.
 */
public class JavaxMail extends Email {

    private String emailProtocol;
    private String gmailLogin;
    private String gmailPassword;

    public JavaxMail(String emailProtocol, String gmailLogin, String gmailPassword) {
        this.emailProtocol = emailProtocol;
        this.gmailLogin = gmailLogin;
        this.gmailPassword = gmailPassword;
    }

    @Override
    public List<Data> collectData() {
        try {
            Folder mailFolder = establishConnection();
            File directory = prepareDirectory();
            checkMailbox(mailFolder, directory);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File prepareDirectory()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSSSSSS");
        Date dt = new Date();
        String datetime = dateFormat.format(dt);

        File dir = new File(datetime);
        if (!dir.exists())
            dir.mkdir();

        return dir;
    }

    private List<String> checkMailbox(Folder folder, File outputDir)
    {
        try {
            Message[] messages = folder.getMessages();

            int msgIndex = 0;
            for (Message message : messages)
            {
                Multipart multipart = (Multipart) message.getContent();
                for (int i = 0; i < multipart.getCount(); i++)
                {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    InputStream inputStream = null;
                    String fileName = null;

                    if (bodyPart.isMimeType("text/plain")  || bodyPart.isMimeType("text/html"))
                    {
                        String nameFormat = "%d-%s-%d.txt";
                        fileName = String.format(nameFormat, msgIndex, "body", i);

                        String body = (String)bodyPart.getContent();
                        inputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
                    }
                    else
                    {
                        if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) &&
                                !StringUtils.isNotBlank(bodyPart.getFileName()))
                        {
                            continue; // dealing with attachments only
                        }
                        inputStream = bodyPart.getInputStream();
                        String nameFormat = "%d-%s-%s";
                        fileName = String.format(nameFormat, msgIndex, "att", bodyPart.getFileName());
                    }

                    if (inputStream != null && fileName != null) {
                        File f = new File(outputDir.getAbsoluteFile() + "\\" + fileName);

                        FileOutputStream fos = new FileOutputStream(f);
                        byte[] buf = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buf)) != -1) {
                            fos.write(buf, 0, bytesRead);
                        }
                        fos.close();
                    }
                }

                msgIndex++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public Folder establishConnection() throws MessagingException {
        Properties properties = new Properties();

        properties.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(properties, null);
        Store store = session.getStore("imaps");
        store.connect(emailProtocol, gmailLogin, gmailPassword);
        IMAPFolder folder = (IMAPFolder) store.getFolder("Inbox");
        folder.open(Folder.READ_ONLY);

        return folder;
    }
}
