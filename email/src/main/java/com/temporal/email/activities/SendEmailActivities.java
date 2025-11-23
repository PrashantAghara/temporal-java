package com.temporal.email.activities;

import com.temporal.email.model.EmailDetails;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SendEmailActivities {
    @ActivityMethod
    String sendEmail(EmailDetails details);
}
