package com.moneylion.featureapi.dto;

public class FeatureRetrieveDTO {
	@SuppressWarnings("unused")
	private String email;
	@SuppressWarnings("unused")
	private String featureName;
	private boolean isExist;

	private FeatureRetrieveDTO() {
	}

	public FeatureRetrieveDTO email(String email) {
		this.email = email;
		return this;
	}

	public FeatureRetrieveDTO featureName(String featureName) {
		this.featureName = featureName;
		return this;
	}

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}

	public String getEmail() {
		return email;
	}

	public String getFeatureName() {
		return featureName;
	}

	public static FeatureRetrieveDTO getInstance() {
		return new FeatureRetrieveDTO();
	}
}
