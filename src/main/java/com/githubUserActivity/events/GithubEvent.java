package com.githubUserActivity.events;

import com.githubUserActivity.GithubEvents;
import org.json.simple.JSONObject;

public class GithubEvent {
    protected final long id;
    protected final GithubEvents event;

    JSONObject eventPayload;

    public GithubEvent(JSONObject obj) {
        id = Long.parseLong((String)obj.get("id"));
        //TODO
        event = GithubEvents.fromValue((String)obj.get("type")).orElse(null);

        eventPayload = obj;
    }

    @Override
    public String toString() {
        return EventMessageFormatter.format(event, eventPayload);
    }
}
