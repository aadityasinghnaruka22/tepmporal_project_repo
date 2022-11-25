package com.ebit.workflow_management_system.serviceImpl;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkFlow {

	public static final String QUEUE_NAME = "Customer_Order";

	@WorkflowMethod
	void startApprovalWorkflow();

	@SignalMethod
	void signalOrderAccepted();

	@SignalMethod
	void signalOrderPickedUp();

	@SignalMethod
	void signalOrderDelivered();
}
