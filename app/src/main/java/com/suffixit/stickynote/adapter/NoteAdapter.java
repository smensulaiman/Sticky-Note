package com.suffixit.stickynote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.textview.MaterialTextView;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private List<Note> notes;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
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
        holder.dateTime.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date(note.getNoteCreatedAt())));

        YoYo.with(Techniques.BounceIn)
                .duration(700)
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

        @BindView(R.id.txtNoteTitle)
        MaterialTextView txtNoteTitle;

        @BindView(R.id.txtNoteDescription)
        MaterialTextView txtNoteDescription;

        @BindView(R.id.dateTime)
        MaterialTextView dateTime;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
