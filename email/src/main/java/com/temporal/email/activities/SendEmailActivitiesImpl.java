package com.temporal.email.activities;

import com.temporal.email.model.EmailDetails;
import io.temporal.spring.boot.ActivityImpl;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@ActivityImpl(workers = "send-email-worker")
public class SendEmailActivitiesImpl implements SendEmailActivities {
    @Override
    public String sendEmail(EmailDetails details) {
        String response = MessageFormat.format("Sending email to {0} with message : {1}, count : {2}", details.getEmail(), details.getMessage(), details.getCount());
        System.out.println(response);
        return "Success";
    }
}
