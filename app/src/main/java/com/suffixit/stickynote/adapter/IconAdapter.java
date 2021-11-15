package com.suffixit.stickynote.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.suffixit.stickynote.R;
import com.suffixit.stickynote.model.IconModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder> {

    private Context context;
    private List<IconModel> icons;
    int selectedPosition = 0;
    private IconListInterface iconListInterface;

    public IconAdapter(Context context, List<IconModel> icons) {
        this.context = context;
        this.icons = icons;
    }

    public void setIconListInterface(IconListInterface listener) {
        this.iconListInterface = listener;
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
        IconModel icon = icons.get(position);

        if (position == selectedPosition) {
            holder.layoutIcon.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green));
        } else {
            holder.layoutIcon.setBackgroundColor(ContextCompat.getColor(context, R.color.icon_background));
        }

        holder.imgIcon.setImageResource(icon.getIcon());
        holder.imgIcon.setOnClickListener(v -> {
            if (position == RecyclerView.NO_POSITION){
                return;
            }
            iconListInterface.onItemClick(position, icon);
            notifyItemChanged(selectedPosition);
            selectedPosition = position;
            notifyItemChanged(selectedPosition);
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
