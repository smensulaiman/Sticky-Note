package com.acjl.foodmenu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.acjl.foodmenu.R;
import com.ramotion.foldingcell.FoldingCell;
import org.jetbrains.annotations.NotNull;

public class LunchListAdapter extends RecyclerView.Adapter<LunchListAdapter.ViewHolder> {

    private Context context;

    public LunchListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_lunch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.foldingCell.setOnClickListener(view -> holder.foldingCell.toggle(false));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.folding_cell)
        FoldingCell foldingCell;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
