package com.githubUserActivity;

import java.util.Optional;

public enum StatusCode {
    OK(200),
    NOT_MODIFIED(304),
    FORBIDDEN(403),
    SERVICE_UNAVAILABLE(503);

    private final int code;

    StatusCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Optional<StatusCode> fromValue(final int value) {
        for (StatusCode status : StatusCode.values()) {
            if (status.getCode() == value) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
