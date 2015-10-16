package com.natint.email;

import org.springframework.stereotype.Component;

/**
 * Created by skn on 16/10/2015.
 */
@Component
public class EmailFactory {
    private String gmailLogin;
    private String gmailPassword;
    private String emailProtocol;
    private String metaLogin;
    private String metaPassword;

    public EmailFactory(String gmailLogin, String gmailPassword, String emailProtocol, String metaLogin, String metaPassword) {
        this.gmailLogin = gmailLogin;
        this.gmailPassword = gmailPassword;
        this.emailProtocol = emailProtocol;
        this.metaLogin = metaLogin;
        this.metaPassword = metaPassword;
    }

    public Email getInstance(String name) {
        switch (name.toUpperCase()) {
            case "GMAIL4J":
                return new Gmail4j(gmailLogin, gmailPassword);
            case "JAVAX":
                return new JavaxMail(emailProtocol, metaLogin, metaPassword);
            default:
                throw new IllegalArgumentException("No matches found for email endpoint " + name);
        }
    }
}
