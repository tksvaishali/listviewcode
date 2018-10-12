package com.listsample.vaishali.listview.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaishali_s.
 * <p>
 * ListDetails is a model class to store webservice response
 */
public class ListDetails {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("rows")
    private List<ListData> mRows;

    /**
     * Default Constructor
     */
    public ListDetails(){
        this.mTitle = "";
        this.mRows = new ArrayList<>();
    }
    /**
     * Constructor to initialise the variables
     *
     * @param pTitle   Title for action bar
     * @param pListData List of ListData
     */
    public ListDetails(String pTitle, List<ListData> pListData) {
        this.mTitle = pTitle;
        this.mRows = pListData;
    }

    /**
     * Get Title
     *
     * @return title for screen action bar
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Set Title
     *
     * @return set title text
     */
    public void setTitle(String title) {
        this.mTitle = title;
    }

    /**
     * This method will return the List of ListData object for listview
     *
     * @return List<ListData>
     */
    public List<ListData> getRows() {
        return mRows;
    }

    /**
     * Set List<ListData> to rows object
     *
     * @param pRowsList List of ListData
     */
    public void setRows(List<ListData> pRowsList) {
        this.mRows = pRowsList;
    }
}
