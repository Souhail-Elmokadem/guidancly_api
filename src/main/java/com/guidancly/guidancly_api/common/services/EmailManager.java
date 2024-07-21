package com.guidancly.guidancly_api.common.services;

public interface EmailManager {
     void sendEmail(String to, String subject, String body);
}
