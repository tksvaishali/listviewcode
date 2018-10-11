package com.example.vaishali.listview.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vaishali.listview.R;
import com.example.vaishali.listview.adapter.ExampleListViewAdapter;
import com.example.vaishali.listview.model.ListData;
import com.example.vaishali.listview.model.ListDetails;
import com.example.vaishali.listview.presenter.ExampleMainPresenter;

import java.util.List;

/**
 * Created by vaishali_s.
 * <p>
 * The ExampleMainActivity class is first activity which will display List and refresh button.
 * Refresh button is used to refresh the list.
 */
public class ExampleMainActivity extends AppCompatActivity implements View.OnClickListener, ExampleMainView {
    private ListView listView;
    private Button btnRefresh;
    private TextView txtHeading;
    private ExampleListViewAdapter adapter;

    private ExampleMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        presenter = new ExampleMainPresenter(this);
        presenter.callWebservice();
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
     * This method will update list
     *
     * @param listData List of ListItem Data
     */
    private void updateList(List<ListData> listData) {
        adapter = new ExampleListViewAdapter(this, listData);
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
            presenter.callWebservice();
        }
    }

    /**
     * This method will display error message in the heading textview
     */
    @Override
    public void displayErrorMessage() {
        txtHeading.setText(getString(R.string.error_message));
    }

    /**
     * This method will update the description text in heading textview
     */
    @Override
    public void updateHeadingText() {
        txtHeading.setText(getString(R.string.fetching_data));
    }

    /**
     * The updateScreen method will update title and list
     *
     * @param listDetails Response of webservice call
     */
    @Override
    public void updateListDetails(ListDetails listDetails) {
        txtHeading.setText(getString(R.string.data_displayed));
        updateActionBarTitle(listDetails.getTitle());
        updateList(listDetails.getRows());
    }
}