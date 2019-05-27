/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:36 AM.
 * Last modified 5/27/19 12:11 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.dataextractor;

import com.embedit.dataextraction.dataextractor.model.DataExtractionResult;
import com.embedit.dataextraction.dataextractor.model.Error;
import com.embedit.dataextraction.dataextractor.model.ExtractedData;
import com.embedit.dataextraction.jsonplaceholder.JSONPlaceholderService;
import com.embedit.dataextraction.jsonplaceholder.UserNotFoundException;
import com.embedit.dataextraction.jsonplaceholder.model.Post;
import com.embedit.dataextraction.jsonplaceholder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataExtractorServiceImpl implements DataExtractorService {
    private final JSONPlaceholderService jsonPlaceholderService;

    @Autowired
    public DataExtractorServiceImpl(JSONPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    @Override
    @Cacheable(value = "DataExtractionResult", key = "#userId")
    public DataExtractionResult extractUserAndPosts(long userId) {
        User user;

        try {
            user = jsonPlaceholderService.findUserById(userId);
        } catch (UserNotFoundException e) {
            return new Error().setErrorMessage(e.getMessage());
        }

        List<Post> posts = jsonPlaceholderService.findPostsByUserId(userId);
        return new ExtractedData()
                .setId(user.getId())
                .setName(user.getName())
                .setUsername(user.getUsername())
                .setPosts(posts);
    }
}
