package com.workflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class Main {
    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker("greeting-tasks");
        worker.registerWorkflowImplementationTypes(GreetImpl.class);
        factory.start();

        // Run from Application itself
//        WorkflowOptions options = WorkflowOptions.newBuilder()
//                .setWorkflowId("application-run")
//                .setTaskQueue("greeting-tasks")
//                .build();
//
//        Greeting workflow = client.newWorkflowStub(Greeting.class, options);
//        String greeting = workflow.greet("Prashant");
//        String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();
//        System.out.println("Workflow Id : " + workflowId);
//        System.out.println(greeting);
    }
}