package com.listsample.vaishali.listview.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaishali_s
 * <p>
 * Unit Test case for testing ListDetails model class
 */
public class ListDetailsTest {

    private ListDetails listDetails;
    private String title = "Beavers";
    private String description = "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony";
    private String imageHref = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg";

    /**
     * Test Case for Valid title test
     */
    @Test
    public void testValidTitle() {
        String title = "Canada";
        listDetails = new ListDetails();
        listDetails.setTitle(title);
        Assert.assertNotNull(listDetails);
        Assert.assertEquals(title, listDetails.getTitle());
    }

    /**
     * Test Case for Valid list data
     */
    @Test
    public void testValidListData() {
        ListData listData = new ListData(title, description, imageHref);

        List<ListData> dataArrayList = new ArrayList<>();
        dataArrayList.add(listData);

        listDetails = new ListDetails();
        listDetails.setRows(dataArrayList);
        Assert.assertNotNull(listDetails);
        Assert.assertEquals(1, listDetails.getRows().size());
    }
}
