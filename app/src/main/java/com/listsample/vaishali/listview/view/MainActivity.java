package com.listsample.vaishali.listview.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.listsample.vaishali.listview.R;
import com.listsample.vaishali.listview.model.ListData;
import com.listsample.vaishali.listview.model.ListDetails;
import com.listsample.vaishali.listview.presenter.MainPresenter;
import com.listsample.vaishali.listview.view.adapter.MainListAdapter;

import java.util.List;

/**
 * Created by vaishali_s.
 * <p>
 * The MainActivity class is first activity which will display List and refresh button.
 * Refresh button is used to refresh the list.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainViewInterface {
    private ListView lvMainList;
    private Button btnRefresh;
    private TextView tvHeading;
    private MainListAdapter mMainListAdapter;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        mPresenter = new MainPresenter(this);
        mPresenter.callWebservice();
    }

    /**
     * This method will initialise UI elements and add listener
     */
    private void initUI() {
        lvMainList = findViewById(R.id.lv_mainlist);
        btnRefresh = findViewById(R.id.btn_refresh);
        tvHeading = findViewById(R.id.tv_heading);

        btnRefresh.setOnClickListener(this);
    }

    /**
     * This method will update list
     *
     * @param listData List of ListItem Data
     */
    private void updateList(List<ListData> listData) {
        mMainListAdapter = new MainListAdapter(this, listData);
        lvMainList.setAdapter(mMainListAdapter);
        mMainListAdapter.notifyDataSetChanged();
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
            mPresenter.callWebservice();
        }
    }

    /**
     * This method will display error message in the heading textview
     */
    @Override
    public void displayErrorMessage() {
        tvHeading.setText(getString(R.string.error_message));
    }

    /**
     * This method will update the description text in heading textview
     */
    @Override
    public void updateHeadingText() {
        tvHeading.setText(getString(R.string.fetching_data));
    }

    /**
     * The updateScreen method will update title and list
     *
     * @param listDetails Response of webservice call
     */
    @Override
    public void updateListDetails(ListDetails listDetails) {
        tvHeading.setText(getString(R.string.data_displayed));
        updateActionBarTitle(listDetails.getTitle());
        updateList(listDetails.getRows());
    }
}