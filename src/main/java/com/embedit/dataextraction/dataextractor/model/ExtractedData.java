/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:36 AM.
 * Last modified 5/27/19 12:31 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.dataextractor.model;

import com.embedit.dataextraction.jsonplaceholder.model.Post;

import java.io.Serializable;
import java.util.List;

public class ExtractedData implements DataExtractionResult, Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String username;
    private List<Post> posts;

    public Long getId() {
        return id;
    }

    public ExtractedData setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExtractedData setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ExtractedData setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public ExtractedData setPosts(List<Post> posts) {
        this.posts = posts;
        return this;
    }
}
