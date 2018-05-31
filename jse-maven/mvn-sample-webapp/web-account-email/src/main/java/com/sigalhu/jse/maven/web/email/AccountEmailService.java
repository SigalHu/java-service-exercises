package com.sigalhu.jse.maven.web.email;

public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText)
            throws AccountEmailException;
}
