package com.guidancly.guidancly_api.common.services;

import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailManager{
    @Override
    public void sendEmail(String to, String subject, String body) {
        //script for send email
    }
}
