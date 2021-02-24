package com.moneylion.featureapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.moneylion.featureapi.utils.DefaultApiException;

@ControllerAdvice
@Component
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DefaultApiException.class)
	protected ResponseEntity<DefaultApiException> handleMyException(DefaultApiException apiError, WebRequest req) {
		System.out.println("DEFAULT EXCEPTION THROWN : \n " + ExceptionUtils.getStackTrace(apiError));
		return new ResponseEntity<DefaultApiException>(apiError, new HttpHeaders(), apiError.getMessage().contains("Validate")?HttpStatus.BAD_REQUEST:HttpStatus.NOT_MODIFIED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		System.out.println("handled-handleMethodArgumentNotValid");
		//
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final DefaultApiException apiError = new DefaultApiException(ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
	}

	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {

		System.out.println("handled-handleMissingServletRequestParameter");

		final List<String> errors = new ArrayList<String>();
		errors.add(ex.getParameterName() + " parameter is missing");
		final DefaultApiException apiError = new DefaultApiException(ex.getLocalizedMessage(), errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<DefaultApiException> handleConstraintViolation(final ConstraintViolationException ex,
			final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		final DefaultApiException apiError = new DefaultApiException(ex.getLocalizedMessage(), errors);
		return new ResponseEntity<DefaultApiException>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
