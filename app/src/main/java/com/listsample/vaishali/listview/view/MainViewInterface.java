package com.listsample.vaishali.listview.view;

import com.listsample.vaishali.listview.model.ListDetails;

/**
 * Created by vaishali_s.
 * <p>
 * This class represents MainViewInterface Interface
 */
public interface MainViewInterface {

    void displayErrorMessage();

    void updateHeadingText();

    void updateListDetails(ListDetails listDetails);
}
