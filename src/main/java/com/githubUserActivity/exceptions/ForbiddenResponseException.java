package com.githubUserActivity.exceptions;

import com.githubUserActivity.StatusCode;

public class ForbiddenResponseException extends ResponseException{
    public ForbiddenResponseException()
    {
        super("Forbidden status code: " + StatusCode.FORBIDDEN);
    }
}
