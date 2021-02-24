package com.moneylion.featureapi.reqresp;

public class FeatureCanAccessResp {
	private Boolean canAccess;

	private FeatureCanAccessResp() {
	}

	public Boolean getCanAccess() {
		return canAccess;
	}

	public void setCanAccess(Boolean canAccess) {
		this.canAccess = canAccess;
	}

	public static FeatureCanAccessResp canAccess() {
		FeatureCanAccessResp resp = new FeatureCanAccessResp();
		resp.setCanAccess(true);
		return resp;
	}

	public static FeatureCanAccessResp cannotAccess() {
		FeatureCanAccessResp resp = new FeatureCanAccessResp();
		resp.setCanAccess(false);
		return resp;
	}
}
