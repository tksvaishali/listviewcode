package com.listsample.vaishali.listview.presenter;

import com.listsample.vaishali.listview.view.MainViewInterface;
import com.listsample.vaishali.listview.model.ListDetails;
import com.listsample.vaishali.listview.network.RetrofitService;

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
     * MainViewInterface Instance
     */
    private MainViewInterface mView;

    /**
     * RetrofitService Instance
     */
    private RetrofitService mRetrofitService;

    /**
     * Constructor which has MainViewInterface Instance parameter
     *
     * @param view MainViewInterface instance
     */
    public MainPresenter(MainViewInterface view) {
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
