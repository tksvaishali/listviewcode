package com.example.vaishali.listview.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vaishali_s.
 * <p>
 * RetrofitService class will have method to create retrofit instance
 */
public class RetrofitService {

    private Retrofit retrofit;

    /**
     * This method creates a new instance of the API interface.
     * @return API Interface
     */
    public APIInterface getAPIInterface() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(APIConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(APIInterface.class);
    }
}
