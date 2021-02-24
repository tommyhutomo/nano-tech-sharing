package com.moneylion.featureapi.operation;

import java.util.Map;

public abstract class AbstractOperation {

	private Map<String, Object> context;

	private AbstractOperation nextOperation;

	public void execute() {
		this.proceed();
		if (nextOperation != null) {
			nextOperation.execute();
		}
	}

	public Map<String, Object> getContext() {
		return context;
	}

	public void setContext(Map<String, Object> context) {
		this.context = context;
	}

	public AbstractOperation getNextOperation() {
		return nextOperation;
	}

	public void setNextOperation(AbstractOperation nextOperation) {
		this.nextOperation = nextOperation;
	}

	public abstract void proceed();
}
