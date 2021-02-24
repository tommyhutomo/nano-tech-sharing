package com.moneylion.featureapi.controller;

import javax.validation.Valid;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moneylion.featureapi.dto.FeatureRetrieveDTO;
import com.moneylion.featureapi.dto.FeatureSaveDTO;
import com.moneylion.featureapi.reqresp.FeatureCanAccessResp;
import com.moneylion.featureapi.reqresp.FeatureReq;
import com.moneylion.featureapi.service.FeatureService;
import com.moneylion.featureapi.utils.DefaultApiException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/feature")
@Validated
public class FeatureController {

	@Autowired
	private FeatureService service;

	@ApiOperation(value = "Check feature can be access", response = FeatureCanAccessResp.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully check"),
			@ApiResponse(code = 500, message = "Internal Error"),
			@ApiResponse(code = 401, message = "Not possible-not implemented") })
	@GetMapping(path = "", produces = "application/json")
	public @ResponseBody FeatureCanAccessResp get(@RequestParam String email, @RequestParam String featureName) throws DefaultApiException {
		if (!EmailValidator.getInstance().isValid(email)) {
			throw new DefaultApiException("Validate: email not valid",null);
		}
		FeatureRetrieveDTO retval = service
				.isFeatureExist(FeatureRetrieveDTO.getInstance().email(email).featureName(featureName));
		return retval.isExist() ? FeatureCanAccessResp.canAccess() : FeatureCanAccessResp.cannotAccess();
	}

	@ApiOperation(value = "Save new feature", response = FeatureCanAccessResp.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert"),
			@ApiResponse(code = 304, message = "Duplicate entries"),
			@ApiResponse(code = 500, message = "Internal Error"),
			@ApiResponse(code = 401, message = "Not possible-not implemented") })
	@PostMapping(path = "", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void post(@Valid @RequestBody FeatureReq feature) throws Exception {
		service.saveNewFeature(FeatureSaveDTO.getInstance().req(feature));

	}
}
