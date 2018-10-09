package com.example.vaishali.listview.domainobject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vaishali_s.
 * <p>
 * ListDetails is a POJO object to store webservice response
 */
public class ListDetails {
    @SerializedName("title")
    private String title;

    @SerializedName("rows")
    private List<ListData> rows;

    /**
     * Get Title
     *
     * @return title for screen action bar
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set Title
     *
     * @return set title text
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method will return the List of ListData object for listview
     *
     * @return List<ListData>
     */
    public List<ListData> getRows() {
        return rows;
    }

    /**
     * Set List<ListData> to rows object
     *
     * @param rows List of ListData
     */
    public void setRows(List<ListData> rows) {
        this.rows = rows;
    }

    /**
     * Constructor to initialise the variables
     *
     * @param ptitle   Title for action bar
     * @param listdata List of ListData
     */
    public ListDetails(String ptitle, List<ListData> listdata) {
        this.title = ptitle;
        this.rows = listdata;
    }
}
