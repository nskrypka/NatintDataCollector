package com.natint.email;

import com.googlecode.gmail4j.GmailClient;
import com.googlecode.gmail4j.GmailConnection;
import com.googlecode.gmail4j.GmailMessage;
import com.googlecode.gmail4j.http.HttpGmailConnection;
import com.googlecode.gmail4j.rss.RssGmailClient;
import com.natint.data.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by skn on 12/10/2015.
 */
public class Gmail4j extends Email {

    private String emailLogin = System.getProperty("NATINT_EMAIL_LOGIN");
    private String emailPassword = System.getProperty("NATINT_EMAIL_PASSWORD");
    private GmailClient client;
    private Map<String, String> params;

    public Gmail4j(Map<String, String> params) {
        this.params = params;
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
