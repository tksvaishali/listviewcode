package com.listsample.vaishali.listview.view;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.listsample.vaishali.listview.R;
import com.listsample.vaishali.listview.RestServiceTestHelper;
import com.listsample.vaishali.listview.network.APIConstants;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Created by vaishali_s.
 * <p>
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


        onView(allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText("About Canada")));
    }

    @Test
    public void testRetryButtonShowsWhenError() throws Exception {
        String fileName = "test_404_not_found.json";

        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withText("Error occurred.. Please check your network connection and refresh.")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
