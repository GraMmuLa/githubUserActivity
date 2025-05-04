package com.githubUserActivity.exceptions;

import com.githubUserActivity.StatusCode;

public class ServiceUnavailableResponseException extends ResponseException{
    public ServiceUnavailableResponseException() {
        super("Service unavailable status code: " + StatusCode.SERVICE_UNAVAILABLE);
    }
}
