/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:36 AM.
 * Last modified 5/26/19 1:08 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.dataextractor;

import com.embedit.dataextraction.dataextractor.model.DataExtractionResult;

public interface DataExtractorService {
    DataExtractionResult extractUserAndPosts(long userId);
}
