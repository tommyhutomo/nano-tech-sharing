package com.moneylion.featureapi.operation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.moneylion.featureapi.dto.FeatureSaveDTO;
import com.moneylion.featureapi.entity.Features;

@Scope(OperationMapFeatureRequestToEntity.SCOPE)
@Component(OperationMapFeatureRequestToEntity.BEAN_NAME)
public class OperationMapFeatureRequestToEntity extends AbstractOperation {

	public static final String BEAN_NAME = "OperationMapFeatureRequestToEntity";
	public static final String SCOPE = "prototype";

	@Override
	public void proceed() {
		FeatureSaveDTO dto = (FeatureSaveDTO) getContext().get(OperationKey.NEW_FEATURE_DTO);
		Features feat = new Features();
		feat.setEmail(dto.getReq().getEmail());
		feat.setName(dto.getReq().getFeatureName());
		feat.setEnable(dto.getReq().isEnable()?1:0);
		getContext().put(OperationKey.MAPPED_NEW_FEATURE_DTO, feat);
	}

}
