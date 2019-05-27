/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:36 AM.
 * Last modified 5/26/19 11:33 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.jsonplaceholder;

import com.embedit.dataextraction.jsonplaceholder.model.Post;
import com.embedit.dataextraction.jsonplaceholder.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class JSONPlaceholderServiceImpl implements JSONPlaceholderService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final RestTemplate restTemplate;

    @Autowired
    public JSONPlaceholderServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        LOGGER.debug("Configuring REST template root URI...");
        restTemplate = restTemplateBuilder
                .rootUri("https://jsonplaceholder.typicode.com")
                .build();
    }

    @Override
    public User findUserById(long id) throws UserNotFoundException {
        User user;

        LOGGER.debug("Finding user by ID '{}'...", id);
        try {
            user = restTemplate.getForObject("/users/{id}", User.class, id);
        } catch (HttpClientErrorException e) { // Exception thrown when HTTP 4xx is received
            throw new UserNotFoundException(String.format("You chose a non-existent user [user id=%d].", id), e);
        }

        if (user == null) {
            throw new IllegalStateException(String.format("REST template failed in findUserById() [user id=%d]:", id));
        }

        return user;
    }

    @Override
    public List<Post> findPostsByUserId(long userId) {
        LOGGER.debug("Finding posts by User ID '{}'...", userId);
        Post[] posts = restTemplate.getForObject("/posts?userId={userId}", Post[].class, userId); // This doesn't return HTTP 4xx
        if (posts == null) {
            throw new IllegalStateException(String.format("REST template failed in findPostsByUserId() [user id=%d]:", userId));
        }

        return Arrays.asList(posts);
    }
}