package com.temporal.email;

import com.temporal.email.model.Constants;
import com.temporal.email.model.EmailDetails;
import com.temporal.email.model.Message;
import com.temporal.email.model.WorkflowData;
import com.temporal.email.workflows.SendEmailWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    WorkflowClient client;

    @PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message startSubscription(@RequestBody WorkflowData data) {
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(data.getEmail())
                .setTaskQueue(Constants.TASK_QUEUE_NAME)
                .build();

        SendEmailWorkflow workflow = client.newWorkflowStub(SendEmailWorkflow.class, options);
        WorkflowClient.start(workflow::run, data);
        return new Message("Resource created successfully");
    }

    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmailDetails getQuery(@RequestParam String email) {
        SendEmailWorkflow workflow = client.newWorkflowStub(SendEmailWorkflow.class, email);
        return workflow.details();
    }

    @DeleteMapping(value = "/unsubscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message endSubscription(@RequestBody WorkflowData data) {
        SendEmailWorkflow workflow = client.newWorkflowStub(SendEmailWorkflow.class, data.getEmail());
        WorkflowStub stub = WorkflowStub.fromTyped(workflow);
        stub.cancel();
        return new Message("Requesting cancellation");
    }
}
