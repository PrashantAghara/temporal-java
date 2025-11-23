package com.temporal.email.workflows;

import com.temporal.email.model.EmailDetails;
import com.temporal.email.model.WorkflowData;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface SendEmailWorkflow {
    @WorkflowMethod
    void run(WorkflowData data);

    @QueryMethod
    EmailDetails details();
}
