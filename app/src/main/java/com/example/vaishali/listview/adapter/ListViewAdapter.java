package com.example.vaishali.listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vaishali.listview.R;
import com.example.vaishali.listview.domainobject.ListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaishali_s.
 * <p>
 * ListViewAdapter is Base Adapter to update list with List Data values
 */
public class ListViewAdapter extends BaseAdapter {
    private List<ListData> listData;
    private Context context;

    /**
     * ViewHolder acts as holder to refer the list row elements
     */
    private static class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView imgImage;
    }

    /**
     * Constructor to initialise variables
     *
     * @param context     Context
     * @param listdetails List of List data
     */
    public ListViewAdapter(Context context, List<ListData> listdetails) {
        this.context = context;
        listData = new ArrayList<>();
        listData.addAll(listdetails);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
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
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_row_item_layout, parent, false);

            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.list_title);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.list_description);
            viewHolder.imgImage = (ImageView) convertView.findViewById(R.id.imageview);

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
        Glide.with(context)
                .load(listData.getImageHref())
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgImage);

        return result;
    }
}