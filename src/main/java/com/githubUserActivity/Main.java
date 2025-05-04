package com.githubUserActivity;

import com.githubUserActivity.events.GithubEvent;

import java.io.IOException;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        GithubUser user = new GithubUser(args[0]);

        user.fetch();

        for(GithubEvent event : user.getEvents()) {
            System.out.println(" - " + event);
        }
    }
}
