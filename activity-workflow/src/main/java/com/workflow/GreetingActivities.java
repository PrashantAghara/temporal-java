package com.workflow;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface GreetingActivities {
    String sayHello(String name);
}
