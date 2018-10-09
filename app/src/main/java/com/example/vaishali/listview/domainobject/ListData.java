package com.example.vaishali.listview.domainobject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vaishali_s.
 * <p>
 * ListData is a POJO object to store details of list item
 */
public class ListData {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imageHref")
    private String imageHref;

    /**
     * Constructor to initialise variables
     *
     * @param ptitle    Title of list item
     * @param pdesc     Description of list item
     * @param pimageUrl Image URL of list item
     */
    public ListData(String ptitle, String pdesc, String pimageUrl) {
        this.title = ptitle;
        this.description = pdesc;
        this.imageHref = pimageUrl;
    }

    /**
     * Get Title
     *
     * @return title of list item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set Title
     *
     * @param title set title text
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Return the description
     *
     * @return description of list item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description
     *
     * @param description set the description text
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the image URL
     *
     * @return image URL
     */
    public String getImageHref() {
        return imageHref;
    }

    /**
     * Set Image URL
     *
     * @param imageHref sets the image URL
     */
    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
