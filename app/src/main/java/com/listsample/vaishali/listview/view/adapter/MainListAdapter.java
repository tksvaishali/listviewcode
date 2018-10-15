package com.listsample.vaishali.listview.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.listsample.vaishali.listview.R;
import com.listsample.vaishali.listview.model.ListData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vaishali_s.
 * <p>
 * MainListAdapter is Base Adapter to update list with List Data values
 */
public class MainListAdapter extends BaseAdapter {
    private List<ListData> mListData;
    private Context mContext;

    /**
     * ViewHolder acts as holder to refer the list row elements
     */
    class ViewHolder {
        @BindView(R.id.tv_title)
        TextView txtTitle;
        @BindView(R.id.tv_description)
        TextView txtDescription;
        @BindView(R.id.iv_imagehref)
        ImageView imgImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    /**
     * Constructor to initialise variables
     *
     * @param context     Context
     * @param listdetails List of List data
     */
    public MainListAdapter(Context context, List<ListData> listdetails) {
        this.mContext = context;
        mListData = new ArrayList<>();
        mListData.addAll(listdetails);
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_listdata_row_, parent, false);
            viewHolder = new ViewHolder(convertView);
            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        ListData listData = (ListData) getItem(position);
        viewHolder.txtTitle.setText(listData.getTitle());
        viewHolder.txtDescription.setText(listData.getDescription());

        /**
         * Glide to efficiently load images in imageview
         */
        Glide.with(mContext)
                .load(listData.getImageHref())
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgImage);

        return result;
    }
}