package com.example.vaishali.listview.network;

import com.example.vaishali.listview.model.ListDetails;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vaishali_s
 * <p>
 * Interface to make retrofit webservice API
 */
public interface APIInterface {
    @GET("facts.json")
    Call<ListDetails> getListData();
}
