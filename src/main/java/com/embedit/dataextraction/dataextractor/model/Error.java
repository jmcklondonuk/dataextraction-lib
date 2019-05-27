/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:36 AM.
 * Last modified 5/27/19 12:31 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.dataextractor.model;

import java.io.Serializable;

public class Error implements DataExtractionResult, Serializable {
    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public Error setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
