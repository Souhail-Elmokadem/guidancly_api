package com.guidancly.guidancly_api.common.services;

public interface EmailManager {
    public void sendEmail(String to, String subject, String body);
}
