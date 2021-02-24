package com.moneylion.featureapi.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moneylion.featureapi.dto.FeatureRetrieveDTO;
import com.moneylion.featureapi.dto.FeatureSaveDTO;
import com.moneylion.featureapi.operation.OperationChainFactory;
import com.moneylion.featureapi.operation.OperationIsFeatureExist;
import com.moneylion.featureapi.operation.OperationKey;
import com.moneylion.featureapi.operation.OperationMapFeatureRequestToEntity;
import com.moneylion.featureapi.operation.OperationSaveNewFeature;
import com.moneylion.featureapi.service.FeatureService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author TommyHutomo
 * implement chain of responsibility pattern
 * https://en.wikipedia.org/wiki/Chain-of-responsibility_pattern
 */
@Component
public class FeatureServiceImpl extends AbstractServiceImpl implements FeatureService {

	private final String RETRIEVE[] = { OperationIsFeatureExist.BEAN_NAME };
	private final String SAVE[] = { OperationIsFeatureExist.BEAN_NAME, OperationMapFeatureRequestToEntity.BEAN_NAME,
			OperationSaveNewFeature.BEAN_NAME };

	@Autowired
	public FeatureServiceImpl(OperationChainFactory factory) {
		super(factory);
	}

	@Override
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") }, fallbackMethod = "isFeatureExistFallback")
	public FeatureRetrieveDTO isFeatureExist(FeatureRetrieveDTO dto) {
		Map<String, Object> context = new HashMap<>();
		context.put(OperationKey.RETRIEVE_DTO, dto);
		getFactory().generateChain(RETRIEVE, context).execute();
		return (FeatureRetrieveDTO) context.get(OperationKey.RETRIEVE_DTO);
	}

	/* (non-Javadoc)
	 * @see com.moneylion.featureapi.service.FeatureService#saveNewFeature(com.moneylion.featureapi.dto.FeatureSaveDTO)
	 * no hytrix required at this point, exception will be handle by exception handler
	 */
	@Override
	public void saveNewFeature(FeatureSaveDTO dto) {
		Map<String, Object> context = new HashMap<>();
		FeatureRetrieveDTO retDto = FeatureRetrieveDTO.getInstance().email(dto.getReq().getEmail())
				.featureName(dto.getReq().getFeatureName());
		context.put(OperationKey.RETRIEVE_DTO, retDto);
		context.put(OperationKey.NEW_FEATURE_DTO, dto);
		getFactory().generateChain(SAVE, context).execute();
	}

	public FeatureRetrieveDTO isFeatureExistFallback(FeatureRetrieveDTO dto) {
		dto.setExist(false);
		return dto;
	}
}
