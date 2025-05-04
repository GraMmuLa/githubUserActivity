package com.githubUserActivity.events;

import com.githubUserActivity.GithubEvents;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EventMessageFormatter {
    public static String format(GithubEvents event, JSONObject eventPayload)
    {
        return switch (event) {
            case PUSH -> String.format("Pushed %d commits to %s", getCommitCount(eventPayload), getUrl(eventPayload));
            default -> String.format("%s %s", getEventName(eventPayload), getUrl(eventPayload));
        };
    }

    private static String getEventName(JSONObject eventPayload)
    {
        return eventPayload.get("type").toString();
    }

    private static int getCommitCount(JSONObject eventPayload)
    {
        JSONArray commits = (JSONArray) ((JSONObject)eventPayload.get("payload")).get("commits");
        return commits.size();
    }

    private static String getUrl(JSONObject eventPayload)
    {
        JSONObject repo = (JSONObject)eventPayload.get("repo");
        return (String)repo.get("url");
    }
}
