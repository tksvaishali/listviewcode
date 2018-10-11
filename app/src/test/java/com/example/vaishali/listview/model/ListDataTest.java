package com.example.vaishali.listview.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by vaishali_s.
 * <p>
 * Unit Test case for testing ListData model object
 */
public class ListDataTest {
    private ListData listData;
    private String title = "Beavers";
    private String description = "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony";
    private String imageHref = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg";

    /**
     * Test Case for Valid title test
     */
    @Test
    public void testValidTitle() {
        listData = new ListData(title, description, imageHref);
        Assert.assertNotNull(listData);
        Assert.assertNotNull(listData.getTitle());
        Assert.assertEquals(title, listData.getTitle());
    }

    /**
     * Test Case for Valid description test
     */
    @Test
    public void testValidDescription() {
        listData = new ListData(title, description, imageHref);
        Assert.assertNotNull(listData);
        Assert.assertNotNull(listData.getDescription());
        Assert.assertEquals(description, listData.getDescription());
    }

    /**
     * Test Case for Valid image url test
     */
    @Test
    public void testValidImageURL() {
        listData = new ListData(title, description, imageHref);
        Assert.assertNotNull(listData);
        Assert.assertNotNull(listData.getImageHref());
        Assert.assertEquals(imageHref, listData.getImageHref());
    }
}
