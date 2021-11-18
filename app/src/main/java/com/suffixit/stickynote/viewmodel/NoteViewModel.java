package com.suffixit.stickynote.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.suffixit.stickynote.model.Note;
import com.suffixit.stickynote.repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private static final String TAG = "NoteViewModel";

    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public void insetNote(Note note) {
        noteRepository.insetNote(note);
    }

    public void updateNote(Note note) {
        Log.d(TAG, "updateNote: "+note.getNoteCategoryId());
        noteRepository.updateNote(note);
    }

    public void deleteNote(Note note) {
        noteRepository.deleteNote(note);
    }

    public void deleteAllNotes() {
        noteRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return noteRepository.getAllNotes();
    }

}
