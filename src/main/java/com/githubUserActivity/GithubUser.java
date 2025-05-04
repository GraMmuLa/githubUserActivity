package com.githubUserActivity;

import com.githubUserActivity.events.GithubEvent;
import com.githubUserActivity.exceptions.ForbiddenResponseException;
import com.githubUserActivity.exceptions.NotModifiedResponseException;
import com.githubUserActivity.exceptions.ServiceUnavailableResponseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GithubUser {
    private final String username;
    private ArrayList<GithubEvent> events = new ArrayList<>();

    private List<String> activity;

    private Logger logger = Logger.getGlobal();

    public GithubUser(String username) {
        this.username = username;
    }

    public void fetch()
    {
        try
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/" + username + "/events")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            JSONArray array = (JSONArray) new JSONParser().parse(response.body());

            // TODO
            StatusCode responseCode = StatusCode.fromValue(response.statusCode()).orElseThrow();

            switch (responseCode)
            {
                case FORBIDDEN:
                    logger.log(Level.SEVERE, "Forbidden", new ForbiddenResponseException());
                    break;
                case NOT_MODIFIED:
                    logger.log(Level.SEVERE, "Not Modified", new NotModifiedResponseException());
                    break;
                case SERVICE_UNAVAILABLE:
                    logger.log(Level.SEVERE, "Service Unavailable", new ServiceUnavailableResponseException());
                    break;
            }

            for(Object o : array)
                events.add(new GithubEvent((JSONObject) o));

        } catch (IOException ex)
        {
            logger.log(Level.SEVERE, "IOException. " + ex.getMessage(), new Throwable(ex));
        } catch (InterruptedException ex)
        {
            logger.log(Level.SEVERE, "InterruptedException. " + ex.getMessage(), new Throwable(ex));
        } catch (ParseException e) {
            logger.severe("Error parsing JSON. " + e.getMessage());
        }
    }

    public ArrayList<GithubEvent> getEvents()
    {
        return events;
    }
}
