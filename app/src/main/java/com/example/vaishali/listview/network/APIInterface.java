package com.example.vaishali.listview.network;

import com.example.vaishali.listview.domainobject.ListDetails;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vaishali_s
 * <p>
 * Interface to make retrofit webservice call
 */
public interface APIInterface {
    @GET("facts.json")
    Call<ListDetails> getListData();
}
