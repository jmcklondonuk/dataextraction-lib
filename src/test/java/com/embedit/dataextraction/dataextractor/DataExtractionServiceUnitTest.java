/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:37 AM.
 * Last modified 5/26/19 1:14 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.dataextractor;

import com.embedit.dataextraction.dataextractor.model.DataExtractionResult;
import com.embedit.dataextraction.jsonplaceholder.JSONPlaceholderService;
import com.embedit.dataextraction.jsonplaceholder.UserNotFoundException;
import com.embedit.dataextraction.jsonplaceholder.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DataExtractionServiceUnitTest {
    @Mock
    private JSONPlaceholderService jsonPlaceholderService;

    @InjectMocks
    private DataExtractorServiceImpl dataExtractorService;

    @Before
    public void setUp() throws Exception {
        User aMockUser = new User();
        when(jsonPlaceholderService.findUserById(1)).thenReturn(aMockUser);
        when(jsonPlaceholderService.findUserById(0)).thenThrow(new UserNotFoundException("You chose a wrong User ID!"));
    }

    @Test
    public void whenCallingExtractUserAndPosts_thenClientExtractsCorrectData() {
        DataExtractionResult result = dataExtractorService.extractUserAndPosts(1);
        assertEquals("Data extraction result must be ExtractedData!", "ExtractedData", result.getClass().getSimpleName());
        assertNotNull(result);
    }

    @Test
    public void whenCallingExtractUserAndPostsWithInvalidUserId_thenClientThrowsUserNotFoundException() {
        DataExtractionResult result = dataExtractorService.extractUserAndPosts(0);
        assertEquals("Data extraction result must be Error!", "Error", result.getClass().getSimpleName());
    }
}
