package com.fourbtech.stickynote.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;
import com.fourbtech.stickynote.R;
import com.fourbtech.stickynote.model.Note;
import com.fourbtech.stickynote.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private List<Note> notes;
    private NoteAdapterInterface noteAdapterInterface;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    public void setNoteAdapterInterface(NoteAdapterInterface noteAdapterInterface) {
        this.noteAdapterInterface = noteAdapterInterface;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Note note = notes.get(position);

        holder.container.setBackgroundResource(note.getNoteBackgroundColor());
        holder.txtNoteTitle.setText(note.getTitle());
        holder.txtNoteDescription.setText(note.getDescription());
        holder.dateTime.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm")
                .format(new Date(note.getNoteCreatedAt())));

        holder.container.setOnClickListener(v -> {
            Log.d("ADAPTER", note.toString());
            noteAdapterInterface.onClickItem(note);
        });

        holder.container.setOnLongClickListener(v -> {

            if (holder.layoutDelete.getVisibility() != View.VISIBLE) {
                holder.layoutDelete.setVisibility(View.VISIBLE);

                YoYo.with(Techniques.BounceIn)
                        .duration(500)
                        .repeat(0)
                        .playOn(holder.btnDelete);

                YoYo.with(Techniques.BounceIn)
                        .duration(500)
                        .repeat(0)
                        .playOn(holder.btnCancel);
            }
            return true;
        });

        holder.btnCancel.setOnClickListener(v -> holder.layoutDelete.setVisibility(View.GONE));
        holder.btnDelete.setOnClickListener(v ->
                {
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context)
                            .setTitle("Alert!!!")
                            .setMessage("Are you sure want to delete?")
                            .setPositiveButton("YES", (dialog, which) -> {
                                holder.layoutDelete.setVisibility(View.GONE);
                                if (noteAdapterInterface != null) {
                                    noteAdapterInterface.onItemDelete(note);
                                }
                            })
                            .setNegativeButton("NO", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
        );

        holder.share.setOnClickListener(v -> Utils.shareText(context, note));

        YoYo.with(Techniques.BounceIn)
                .duration(500)
                .repeat(0)
                .playOn(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.container)
        ConstraintLayout container;

        @BindView(R.id.layoutDelete)
        RelativeLayout layoutDelete;

        @BindView(R.id.txtNoteTitle)
        MaterialTextView txtNoteTitle;

        @BindView(R.id.txtNoteDescription)
        MaterialTextView txtNoteDescription;

        @BindView(R.id.btnDelete)
        MaterialButton btnDelete;

        @BindView(R.id.btnCancel)
        MaterialButton btnCancel;

        @BindView(R.id.dateTime)
        MaterialTextView dateTime;

        @BindView(R.id.share)
        ImageView share;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
