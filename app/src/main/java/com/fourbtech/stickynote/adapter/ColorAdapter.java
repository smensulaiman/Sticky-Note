package com.fourbtech.stickynote.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fourbtech.stickynote.R;
import com.fourbtech.stickynote.model.ColorModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.IconViewHolder> {

    private Context context;
    private List<ColorModel> colorModels;
    int selectedPosition = 0;
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
                .inflate(R.layout.layout_icon, parent, false));
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ColorModel colorModel = colorModels.get(position);

        if (position == selectedPosition) {
            holder.imgIcon.setVisibility(View.VISIBLE);
            holder.imgIcon.setColorFilter(R.color.color_black);
            holder.layoutIcon.setBackgroundColor(context.getColor(colorModel.getColor()));
            holder.layoutIcon.setPadding(2, 2, 2, 2);
        } else {
            holder.imgIcon.setVisibility(View.INVISIBLE);
            holder.layoutIcon.setBackgroundColor(context.getColor(colorModel.getColor()));
            holder.layoutIcon.setPadding(0, 0, 0, 0);
        }

        holder.layoutIcon.setOnClickListener(v -> {
            if (position == RecyclerView.NO_POSITION) {
                return;
            }
            colorListInterface.onItemClick(position, colorModel);
            selectedPosition = position;
            notifyDataSetChanged();
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
            ButterKnife.bind(this, itemView);
        }
    }
}
