package com.moneylion.featureapi.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.moneylion.featureapi.dto.FeatureRetrieveDTO;
import com.moneylion.featureapi.entity.Features;
import com.moneylion.featureapi.repository.FeatureRepository;
import com.moneylion.featureapi.utils.DefaultApiException;

@Scope(OperationSaveNewFeature.SCOPE)
@Component(OperationSaveNewFeature.BEAN_NAME)
public class OperationSaveNewFeature extends AbstractOperation {

	public static final String BEAN_NAME = "OperationSaveNewFeature";
	public static final String SCOPE = "prototype";

	private FeatureRepository repository;

	@Autowired
	public OperationSaveNewFeature(FeatureRepository repository) {
		this.repository = repository;
	}

	@Override
	public void proceed() {
		FeatureRetrieveDTO retDto = (FeatureRetrieveDTO) getContext().get(OperationKey.RETRIEVE_DTO);
		Features features = (Features) getContext().get(OperationKey.MAPPED_NEW_FEATURE_DTO);
		if (retDto.isExist()) {
			throw new DefaultApiException(BEAN_NAME+" : Not allow to save, same feature already exist",null);
		}
		try {
			repository.save(features);
		} catch (Exception e) {
			throw new DefaultApiException(BEAN_NAME+ ": Unable to insert db", null);
		}
	}

}
