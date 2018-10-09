package com.example.vaishali.listview;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.vaishali.listview.activity.MainActivity;
import com.example.vaishali.listview.network.APIConstants;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by vaishali_s.
 *
 * This class provides unit test cases.
 * Test case for Success and Failure scenario is added.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        APIConstants.BASE_URL = server.url("/").toString();
    }

    @Test
    public void testSuccessResponse() throws Exception {
        String fileName = "test_200_ok_response.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getContext(), fileName)));


        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.heading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.heading)).check(matches(withText("List data displayed below")));
    }

    @Test
    public void testRetryButtonShowsWhenError() throws Exception {
        String fileName = "test_404_not_found.json";

        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.heading)).check(matches(isDisplayed()));
        onView(withId(R.id.heading)).check(matches(withText("Error occurred.. Please check your network connection and refresh.")));
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
