package com.numbrcase;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.test.AndroidTestCase;
import android.test.ProviderTestCase2;
import android.test.RenamingDelegatingContext;
import android.test.ServiceTestCase;
import android.test.mock.MockApplication;
import android.test.mock.MockContentResolver;
import android.util.Log;

import com.numbrcase.database.ContactDB;
import com.numbrcase.model.Contact;
import com.numbrcase.model.ContactImpl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void insertContact() throws Exception {
//        db = new ContactDB(context);
//
//        Contact contact = new ContactImpl();
//        contact.setName("Name");
//        contact.setPhone("999 9999 9999");
//        contact.setEmail("test@test.com");
//        contact.setRequestPlace("Street Test");
//        contact.setStatus(Contact.ADDED);
//
//        boolean result = db.insertContact(contact);
//
//        assertEquals(result, true);
    }




}