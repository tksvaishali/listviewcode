package com.example.vaishali.listview.view;

import com.example.vaishali.listview.model.ListDetails;

/**
 * Created by vaishali_s.
 * <p>
 * This class represents ExampleMainView Interface
 */
public interface ExampleMainView {

    void displayErrorMessage();

    void updateHeadingText();

    void updateListDetails(ListDetails listDetails);
}