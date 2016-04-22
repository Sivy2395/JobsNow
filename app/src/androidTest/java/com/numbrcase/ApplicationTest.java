package com.numbrcase;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;

import com.numbrcase.database.ContactDB;
import com.numbrcase.model.Contact;
import com.numbrcase.model.ContactImpl;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}