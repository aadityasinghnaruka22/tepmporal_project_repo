package com.ebit.workflow_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.ebit.workflow_management_system.serviceImpl.Activity;
import com.ebit.workflow_management_system.serviceImpl.WorkFlow;
import com.ebit.workflow_management_system.serviceImpl.WorkflowImpl;

import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

@SpringBootApplication
public class WorkflowManagementSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext appContext = SpringApplication.run(WorkflowManagementSystemApplication.class, args);
		WorkerFactory factory = appContext.getBean(WorkerFactory.class);
		Activity signUpActivity = appContext.getBean(Activity.class);
		Worker worker = factory.newWorker(WorkFlow.QUEUE_NAME);
		worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
		worker.registerActivitiesImplementations(signUpActivity);
		factory.start();
	}

}
