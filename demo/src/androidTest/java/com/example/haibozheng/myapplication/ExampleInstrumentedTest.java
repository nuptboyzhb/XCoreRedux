package com.example.haibozheng.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.haibozheng.myapplication.actions.ContactsAction;
import com.example.haibozheng.myapplication.model.Title;
import com.github.nuptboyzhb.xcore.eventbus.XCoreBus;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        appContext.startActivity(new Intent(appContext,RecyclerViewActivity.class));
    }
}
