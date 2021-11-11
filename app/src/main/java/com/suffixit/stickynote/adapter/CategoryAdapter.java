package com.suffixit.stickynote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.suffixit.stickynote.R;
import com.suffixit.stickynote.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter {

    private int itemLayout;
    private Context mContext;
    private List<CategoryModel> dataList;

    public CategoryAdapter(@NonNull Context context, int resource, int id, @NonNull List<CategoryModel> dataList) {
        super(context, resource, id, dataList);
        this.mContext = context;
        this.itemLayout = resource;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(itemLayout, parent, false);
        TextView textView = view.findViewById(R.id.txtCategoryTitle);
        ImageView imgCategory = view.findViewById(R.id.imgCategory);
        textView.setText(dataList.get(position).getCategoryTitle());
        imgCategory.setImageResource(dataList.get(position).getIcon());
        return view;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    public void setDataList(List<CategoryModel> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }
}
