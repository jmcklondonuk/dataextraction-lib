/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:37 AM.
 * Last modified 5/26/19 1:39 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.jsonplaceholder;

import com.embedit.dataextraction.jsonplaceholder.model.Post;
import com.embedit.dataextraction.jsonplaceholder.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(JSONPlaceholderServiceImpl.class)
public class JSONPlaceholderServiceUnitTest {
    @Autowired
    private JSONPlaceholderService client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @SpringBootApplication
    static class TestConfiguration {
    }

    @Before
    public void setUp() {
        server.reset();
    }

    @Test
    public void whenCallingGetUser_thenClientMakesCorrectCall() throws Exception {
        User expectedUser = new User();
        expectedUser.setName("Jack McKenzie");
        expectedUser.setUsername("jackmckenzie");

        String jsonStringUser = objectMapper.writeValueAsString(expectedUser);

        server.expect(requestTo("/users/1"))
                .andRespond(withSuccess(jsonStringUser, MediaType.APPLICATION_JSON));

        User actualUser = client.findUserById(1);
        assertEquals("User has an invalid name!", "Jack McKenzie", actualUser.getName());
        assertEquals("User has an invalid username!", "jackmckenzie", actualUser.getUsername());
    }

    @Test
    public void whenCallingGetPosts_thenClientMakesCorrectCall() throws Exception {
        Post[] expectedPosts = {new Post()
                .setId(1L)
                .setTitle("Sample post!")
        };

        String jsonStringPosts = objectMapper.writeValueAsString(expectedPosts);

        server.expect(requestTo("/posts?userId=1"))
                .andRespond(withSuccess(jsonStringPosts, MediaType.APPLICATION_JSON));

        List<Post> actualPosts = client.findPostsByUserId(1);
        assertNotNull("User must have posts!", actualPosts);
        assertEquals("User must have exactly 1 post!", 1, actualPosts.size());
        assertThat("Post id must be 1", actualPosts.get(0).getId(), is(1L));
        assertEquals("Post title must be 'Sample post!'", "Sample post!", actualPosts.get(0).getTitle());
    }
}