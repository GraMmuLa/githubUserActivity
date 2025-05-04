package com.githubUserActivity;

import com.githubUserActivity.events.GithubEvent;

import java.util.Optional;

public enum GithubEvents {
    PUSH("PushEvent"),
    OPENED_ISSUE("OpenedIssue"),
    CREATE_EVENT("CreateEvent"),
    PULL_REQUEST("PullRequestEvent"),
    PULL_REQUEST_REVIEW("PullRequestReviewEvent"),
    PULL_REQUEST_REVIEW_COMMENT("PullRequestReviewCommentEvent");

    private final String eventName;

    GithubEvents(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public static Optional<GithubEvents> fromValue(String eventName) {
        for(GithubEvents event : GithubEvents.values()) {
            if(event.getEventName().equals(eventName)) {
                return Optional.of(event);
            }
        }
        return Optional.empty();
    }

}
