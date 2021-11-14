package com.suffixit.stickynote.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.suffixit.stickynote.R;
import com.suffixit.stickynote.model.Icon;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder> {

    private Context context;
    private List<Icon> icons;
    int selected_position = 0;
    private ColorListInterface colorListInterface;

    public IconAdapter(Context context, List<Icon> icons) {
        this.context = context;
        this.icons = icons;
    }

    public void setColorListInterface(ColorListInterface listener) {
        this.colorListInterface = listener;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IconViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.layout_icon,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Icon icon = icons.get(position);

        if (position == selected_position) {
            holder.layoutIcon.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green));
        } else {
            holder.layoutIcon.setBackgroundColor(ContextCompat.getColor(context, R.color.icon_background));
        }

        holder.imgIcon.setImageResource(icon.getIcon());
        holder.imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == RecyclerView.NO_POSITION){
                    return;
                }
                colorListInterface.onItemClick(position, icon);
                notifyItemChanged(selected_position);
                selected_position = position;
                notifyItemChanged(selected_position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return icons.size();
    }



    public class IconViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgIcon)
        ImageView imgIcon;

        @BindView(R.id.layoutIcon)
        ConstraintLayout layoutIcon;
        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
