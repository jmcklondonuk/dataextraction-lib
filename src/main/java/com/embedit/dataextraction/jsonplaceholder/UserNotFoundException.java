/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:36 AM.
 * Last modified 5/26/19 1:01 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.jsonplaceholder;

public class UserNotFoundException extends Exception {
    public static final long serialVersionUID = 42L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
