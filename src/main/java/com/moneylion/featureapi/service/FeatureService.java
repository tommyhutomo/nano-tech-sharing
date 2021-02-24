package com.moneylion.featureapi.service;

import com.moneylion.featureapi.dto.FeatureRetrieveDTO;
import com.moneylion.featureapi.dto.FeatureSaveDTO;

public interface FeatureService {
	FeatureRetrieveDTO isFeatureExist(FeatureRetrieveDTO dto);
	void saveNewFeature(FeatureSaveDTO dto);
}
