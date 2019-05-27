/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:36 AM.
 * Last modified 5/26/19 1:06 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.jsonplaceholder;

import com.embedit.dataextraction.jsonplaceholder.model.Post;
import com.embedit.dataextraction.jsonplaceholder.model.User;

import java.util.List;

public interface JSONPlaceholderService {
    User findUserById(long id) throws UserNotFoundException;

    List<Post> findPostsByUserId(long userId);
}
