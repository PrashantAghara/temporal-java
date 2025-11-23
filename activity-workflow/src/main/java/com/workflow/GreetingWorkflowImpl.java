package com.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class GreetingWorkflowImpl implements GreetingWorkflow {
    RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(15))
            .setBackoffCoefficient(2.0)
            .setMaximumInterval(Duration.ofSeconds(60))
            .setMaximumAttempts(100)
            .build();

    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .setRetryOptions(retryOptions)
            .build();

    private final GreetingActivities activities = Workflow.newActivityStub(GreetingActivities.class, options);

    @Override
    public String greetSomeone(String name) {
        String spanishGreeting = activities.sayHello(name);
        // TODO: uncomment the line below and change it to execute the Activity method you created
        String spanishFarewell = activities.sayHello(name);
        return "\n" + spanishGreeting + "\n" + spanishFarewell;
    }
}
