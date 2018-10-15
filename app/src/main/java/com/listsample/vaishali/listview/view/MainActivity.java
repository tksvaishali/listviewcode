package com.listsample.vaishali.listview.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.listsample.vaishali.listview.R;
import com.listsample.vaishali.listview.model.ListData;
import com.listsample.vaishali.listview.model.ListDetails;
import com.listsample.vaishali.listview.presenter.MainPresenter;
import com.listsample.vaishali.listview.view.adapter.MainListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vaishali_s.
 * <p>
 * The MainActivity class is first activity which will call webservice and display the data in the List
 */
public class MainActivity extends AppCompatActivity implements MainViewInterface, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.lv_mainlist)
    ListView lvMainList;

    @BindView(R.id.tv_heading)
    TextView tvHeading;

    @BindView(R.id.srl_pulltorefresh)
    SwipeRefreshLayout mPulltoRefresh;

    private MainListAdapter mMainListAdapter;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addListener();

        mPresenter = new MainPresenter(this);
        mPresenter.callWebservice();
    }

    /**
     * This method will add listener
     */
    private void addListener() {
        mPulltoRefresh.setOnRefreshListener(this);
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

    /**
     * Callback for pull to refresh
     */
    @Override
    public void onRefresh() {
        mPresenter.callWebservice();
        mPulltoRefresh.setRefreshing(false);
    }
}