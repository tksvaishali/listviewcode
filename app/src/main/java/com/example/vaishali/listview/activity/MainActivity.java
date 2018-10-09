package com.example.vaishali.listview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vaishali.listview.R;
import com.example.vaishali.listview.adapter.ListViewAdapter;
import com.example.vaishali.listview.domainobject.ListData;
import com.example.vaishali.listview.domainobject.ListDetails;
import com.example.vaishali.listview.network.APIConstants;
import com.example.vaishali.listview.network.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vaishali_s.
 * <p>
 * The MainActivity class is first activity which will display List and refresh button.
 * Refresh button is used to refresh the list.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private APIInterface apiInterface;
    private ListView listView;
    private Button btnRefresh;
    private TextView txtHeading;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        callWebservice();
    }

    /**
     * This method will initialise UI elements and add listener
     */
    private void initUI() {
        listView = findViewById(R.id.listview);
        btnRefresh = findViewById(R.id.refresh);
        txtHeading = findViewById(R.id.heading);

        btnRefresh.setOnClickListener(this);
    }

    /**
     * This method will invoke the webservice call and perform action based on response received
     */
    public void callWebservice() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        txtHeading.setText(getString(R.string.fetching_data));
        apiInterface = retrofit.create(APIInterface.class);

        Call<ListDetails> call = apiInterface.getListData();

        call.enqueue(new Callback<ListDetails>() {
            @Override
            public void onResponse(Call<ListDetails> call, Response<ListDetails> response) {
                if (response.isSuccessful()) {
                    ListDetails listDetails = response.body();
                    updateScreen(listDetails);
                } else {
                    displayErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<ListDetails> call, Throwable t) {
                displayErrorMessage();
                call.cancel();
            }
        });
    }

    /**
     * This method will display error message
     */
    private void displayErrorMessage() {
        txtHeading.setText(getString(R.string.error_message));
    }

    /**
     * The updateScreen method will update title and list
     *
     * @param listDetails Response of webservice call
     */
    private void updateScreen(ListDetails listDetails) {
        txtHeading.setText(getString(R.string.data_displayed));
        updateActionBarTitle(listDetails.getTitle());
        updateList(listDetails.getRows());
    }

    /**
     * This method will update list
     *
     * @param listData List of ListItem Data
     */
    private void updateList(List<ListData> listData) {
        adapter = new ListViewAdapter(this, listData);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * This method will update action bar title
     *
     * @param title Title to be displayed
     */
    private void updateActionBarTitle(String title) {
        setTitle(title);
    }

    @Override
    public void onClick(View view) {
        if (view == btnRefresh) {
            callWebservice();
        }
    }
}