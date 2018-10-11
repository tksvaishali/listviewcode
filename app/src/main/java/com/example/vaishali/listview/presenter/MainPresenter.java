package com.example.vaishali.listview.presenter;

import com.example.vaishali.listview.view.MainView;
import com.example.vaishali.listview.model.ListDetails;
import com.example.vaishali.listview.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vaishali_s.
 * <p>
 * MainPresenter class is the Presenter between view and model.
 */
public class MainPresenter {
    /**
     * MainView Instance
     */
    private MainView mView;

    /**
     * RetrofitService Instance
     */
    private RetrofitService mRetrofitService;

    /**
     * Constructor which has MainView Instance parameter
     *
     * @param view MainView instance
     */
    public MainPresenter(MainView view) {
        this.mView = view;
        if (this.mRetrofitService == null) {
            this.mRetrofitService = new RetrofitService();
        }
    }

    /**
     * This method will invoke the webservice call using retrofit service
     */
    public void callWebservice() {
        mView.updateHeadingText();
        mRetrofitService.getAPIInterface().getListData().enqueue(new Callback<ListDetails>() {
            @Override
            public void onResponse(Call<ListDetails> call, Response<ListDetails> response) {
                if (response.isSuccessful()) {
                    ListDetails listDetails = response.body();
                    mView.updateListDetails(listDetails);
                } else {
                    mView.displayErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<ListDetails> call, Throwable t) {
                mView.displayErrorMessage();
                call.cancel();
            }
        });
    }
}
