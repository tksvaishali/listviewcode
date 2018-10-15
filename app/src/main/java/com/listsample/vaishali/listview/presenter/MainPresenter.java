package com.listsample.vaishali.listview.presenter;

import com.listsample.vaishali.listview.model.ListData;
import com.listsample.vaishali.listview.model.ListDetails;
import com.listsample.vaishali.listview.network.RetrofitService;
import com.listsample.vaishali.listview.view.MainViewInterface;

import java.util.ArrayList;
import java.util.List;

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
        mRetrofitService.getAPIInterface().getListData().enqueue(new Callback<ListDetails>() {
            @Override
            public void onResponse(Call<ListDetails> call, Response<ListDetails> response) {
                if (response.isSuccessful()) {
                    ListDetails listDetails = createListData(response.body());
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

    /**
     * Create valid list data to be displayed in the list
     *
     * @param listDetails response from webservice
     * @return ListDetails object which has valid list data to be displayed
     */
    public ListDetails createListData(ListDetails listDetails) {
        List<ListData> listRow = new ArrayList<>();
        List<ListData> displayListRow = new ArrayList<>();
        listRow.addAll(listDetails.getRows());

        for (int i = 0; i < listRow.size(); i++) {
            ListData listData = listRow.get(i);
            if (listData.getTitle() != null || listData.getTitle() != null || listData.getTitle() != null) {
                displayListRow.add(listData);
            }
        }
        listDetails.setRows(displayListRow);

        return listDetails;
    }
}