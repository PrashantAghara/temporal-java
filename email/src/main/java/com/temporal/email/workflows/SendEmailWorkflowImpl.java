package com.temporal.email.workflows;

import com.temporal.email.activities.SendEmailActivities;
import com.temporal.email.model.EmailDetails;
import com.temporal.email.model.WorkflowData;
import io.temporal.activity.ActivityOptions;
import io.temporal.failure.CanceledFailure;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.CancellationScope;
import io.temporal.workflow.Workflow;

import java.time.Duration;

@WorkflowImpl(workers = "send-email-worker")
public class SendEmailWorkflowImpl implements SendEmailWorkflow {
    private EmailDetails emailDetails = new EmailDetails();

    private final ActivityOptions options =
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .build();

    private final SendEmailActivities activities =
            Workflow.newActivityStub(SendEmailActivities.class, options);

    @Override
    public void run(WorkflowData data) {
        int duration = 12;
        emailDetails.setEmail(data.getEmail());
        emailDetails.setMessage("Welcome to our Subscription Workflow!");
        emailDetails.setSubscribed(true);
        emailDetails.setCount(0);

        while (emailDetails.isSubscribed()) {
            emailDetails.setCount(emailDetails.getCount() + 1);

            if (emailDetails.getCount() > 1) {
                emailDetails.setMessage("Thank you for staying subscribed!");
            }

            try {
                activities.sendEmail(emailDetails);
                Workflow.sleep(Duration.ofSeconds(duration));
            } catch (CanceledFailure canceledFailure) {
                emailDetails.setSubscribed(false);
                emailDetails.setMessage("Sorry to see you go");
                CancellationScope sendGoodBye = Workflow.newDetachedCancellationScope(() -> activities.sendEmail(emailDetails));
                sendGoodBye.run();
                throw canceledFailure;
            }
        }
    }

    @Override
    public EmailDetails details() {
        return emailDetails;
    }
}
