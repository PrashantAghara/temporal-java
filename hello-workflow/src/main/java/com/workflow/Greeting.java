package com.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface Greeting {
    @WorkflowMethod
    String greet(String name);
}
