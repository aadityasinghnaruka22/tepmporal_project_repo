package com.ebit.workflow_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.ebit.workflow_management_system.serviceImpl.ActivityImpl;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;

@Component
@Configuration
public class TemporalConfig {

	private String temporalServiceAddress = "127.0.0.1:7233";
//	private String temporalServiceAddress = "localhost:8082";

	private String temporalNamespace = "TemporalData";

	@SuppressWarnings("deprecation")
	@Bean
	public WorkflowServiceStubs workflowServiceStubs() {
		return WorkflowServiceStubs
				.newInstance(WorkflowServiceStubsOptions.newBuilder().setTarget(temporalServiceAddress).build());
	}

	@Bean
	public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
		return WorkflowClient.newInstance(workflowServiceStubs,
				WorkflowClientOptions.newBuilder().setNamespace(temporalNamespace).build());
	}

	@Bean
	public WorkerFactory workerFactory(WorkflowClient workflowClient) {
		return WorkerFactory.newInstance(workflowClient);
	}

	@Bean
	public ActivityImpl SignUpActivity() {
		return new ActivityImpl();
	}
}
