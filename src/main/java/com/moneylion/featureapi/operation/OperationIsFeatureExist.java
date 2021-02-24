package com.moneylion.featureapi.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.moneylion.featureapi.dto.FeatureRetrieveDTO;
import com.moneylion.featureapi.repository.FeatureRepository;

@Scope(OperationIsFeatureExist.SCOPE)
@Component(OperationIsFeatureExist.BEAN_NAME)
public class OperationIsFeatureExist extends AbstractOperation {

	public static final String BEAN_NAME = "OperationIsFeatureExist";
	public static final String SCOPE = "prototype";

	private FeatureRepository repository;

	@Autowired
	public OperationIsFeatureExist(FeatureRepository repository) {
		this.repository = repository;
	}

	@Override
	public void proceed() {
		FeatureRetrieveDTO dto = (FeatureRetrieveDTO) getContext().get(OperationKey.RETRIEVE_DTO);
		dto.setExist(repository.countByEmailAndName(dto.getEmail(), dto.getFeatureName()) > 0 ? true : false);
	}

}
