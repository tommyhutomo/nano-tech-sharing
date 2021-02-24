package com.moneylion.featureapi.service.impl;

import com.moneylion.featureapi.operation.OperationChainFactory;

public abstract class AbstractServiceImpl {
	
	protected OperationChainFactory factory;
	
	public AbstractServiceImpl(OperationChainFactory factory) {
		this.factory = factory;
	}
	
	public OperationChainFactory getFactory() {
		return factory;
	}
	public void setFactory(OperationChainFactory factory) {
		this.factory = factory;
	}
	
}
