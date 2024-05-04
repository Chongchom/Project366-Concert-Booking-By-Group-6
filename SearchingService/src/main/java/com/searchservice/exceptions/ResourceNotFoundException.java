package com.searchservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException() {
		super("Resource notfound on server !!");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}