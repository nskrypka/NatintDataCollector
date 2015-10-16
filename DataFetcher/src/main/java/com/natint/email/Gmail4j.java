package com.natint.email;

import com.googlecode.gmail4j.GmailClient;
import com.googlecode.gmail4j.GmailConnection;
import com.googlecode.gmail4j.GmailMessage;
import com.googlecode.gmail4j.http.HttpGmailConnection;
import com.googlecode.gmail4j.rss.RssGmailClient;
import com.natint.data.Data;

import java.util.List;

/**
 * Created by skn on 12/10/2015.
 */
public class Gmail4j extends Email {

    private String emailLogin;
    private String emailPassword;
    private GmailClient client;

    public Gmail4j(String gmailEmail, String gmailPassword) {
        this.emailLogin = gmailEmail;
        this.emailPassword = gmailPassword;
        client = new RssGmailClient();
        GmailConnection connection = new HttpGmailConnection(emailLogin, emailPassword.toCharArray());
        client.setConnection(connection);
    }

    @Override
    public List<Data> collectData() {
        List<GmailMessage> messages = client.getUnreadMessages();
        for (GmailMessage message : messages) {
            System.out.println(message.getPreview());
        }
        return null;
    }
}
