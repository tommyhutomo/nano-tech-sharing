package com.moneylion.featureapi.dto;

import com.moneylion.featureapi.reqresp.FeatureReq;

public class FeatureSaveDTO {
	private FeatureReq req;
	
	private FeatureSaveDTO() {}

	public FeatureReq getReq() {
		return req;
	}
	public FeatureSaveDTO req(FeatureReq req) {
		this.req = req;
		return this;
	}
	public static FeatureSaveDTO getInstance() {
		return new FeatureSaveDTO();
	}
}
