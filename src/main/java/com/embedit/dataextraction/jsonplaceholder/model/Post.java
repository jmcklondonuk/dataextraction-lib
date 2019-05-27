/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:36 AM.
 * Last modified 5/27/19 12:40 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.jsonplaceholder.model;

import java.io.Serializable;

public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }
}
