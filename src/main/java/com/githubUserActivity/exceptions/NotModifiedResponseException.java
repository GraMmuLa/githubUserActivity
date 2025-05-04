package com.githubUserActivity.exceptions;

import com.githubUserActivity.StatusCode;

public class NotModifiedResponseException extends ResponseException{
    public NotModifiedResponseException()
    {
        super("Not Modified status code: " + StatusCode.NOT_MODIFIED);
    }
}
