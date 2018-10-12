package com.listsample.vaishali.listview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vaishali_s.
 * <p>
 * ListData is a model class to store details of list item
 */
public class ListData {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("imageHref")
    private String mImageHref;

    /**
     * Constructor to initialise variables
     *
     * @param pTitle    Title of list item
     * @param pDesc     Description of list item
     * @param pImageUrl Image URL of list item
     */
    public ListData(String pTitle, String pDesc, String pImageUrl) {
        this.mTitle = pTitle;
        this.mDescription = pDesc;
        this.mImageHref = pImageUrl;
    }

    /**
     * Get Title
     *
     * @return title of list item
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Set Title
     *
     * @param mTitle title text
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Return the description
     *
     * @return description of list item
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Set the description
     *
     * @param mDescription the description text
     */
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    /**
     * Return the image URL
     *
     * @return image URL
     */
    public String getImageHref() {
        return mImageHref;
    }

    /**
     * Set Image URL
     *
     * @param mImageHref the image URL
     */
    public void setImageHref(String mImageHref) {
        this.mImageHref = mImageHref;
    }
}
