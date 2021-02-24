package com.moneylion.featureapi.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OperationChainFactory {
	ApplicationContext appContext;

	@Autowired
	public OperationChainFactory(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	public AbstractOperation generateChain(String Operations[], Map<String, Object> context) {
		List<AbstractOperation> chain = new ArrayList<AbstractOperation>();
		for (int i = 0; i <= Operations.length - 1; i++) {
			AbstractOperation Operation=(AbstractOperation) appContext.getBean(Operations[i]);
			Operation.setContext(context);
			chain.add(Operation);
		}
		
		for (int i = 0; i <= Operations.length - 1; i++) {
			AbstractOperation Operation = chain.get(i);
			Operation.setNextOperation(i+1>Operations.length-1? null : chain.get(i+1));
		}
		return chain.get(0);
	}
}
