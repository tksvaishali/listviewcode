package com.example.vaishali.listview.view;

import com.example.vaishali.listview.model.ListDetails;

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
