package com.moneylion.featureapi.reqresp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class FeatureReq {
	@NotBlank(message = "featureName is mandatory")
	private String featureName;
	
	@NotBlank(message = "email is mandatory")
	@Email(message="email not correct")
	private String email;
	
	private boolean enable;
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
}
