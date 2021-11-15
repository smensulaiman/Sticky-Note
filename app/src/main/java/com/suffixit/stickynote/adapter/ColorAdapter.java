package com.suffixit.stickynote.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.suffixit.stickynote.R;
import com.suffixit.stickynote.model.ColorModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.IconViewHolder> {

    private Context context;
    private List<ColorModel> colorModels;
    int selected_position = 0;
    private ColorListInterface colorListInterface;

    public ColorAdapter(Context context, List<ColorModel> colorModels) {
        this.context = context;
        this.colorModels = colorModels;
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

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ColorModel colorModel = colorModels.get(position);


        if (position == selected_position) {
            holder.layoutIcon.setBackgroundColor(Color.parseColor(colorModel.getColor()));
            holder.layoutIcon.setPadding(2,4,2,0);
        }else {
            holder.layoutIcon.setBackgroundColor(Color.parseColor(colorModel.getColor()));
            holder.layoutIcon.setPadding(0,0,0,0);

        }
        holder.imgIcon.setVisibility(View.INVISIBLE);
        holder.layoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == RecyclerView.NO_POSITION){
                    return;
                }
                colorListInterface.onItemClick(position, colorModel);
                notifyItemChanged(selected_position);
                selected_position = position;
                notifyItemChanged(selected_position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return colorModels.size();
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
