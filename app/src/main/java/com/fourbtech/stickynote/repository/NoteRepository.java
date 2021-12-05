package com.fourbtech.stickynote.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.fourbtech.stickynote.model.Note;
import com.fourbtech.stickynote.database.NoteDatabase;

import java.util.List;

public class NoteRepository {
    private static final String TAG = "NoteRepository";

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insetNote(Note note) {
        new InsertNoteAsync(noteDao).execute(note);
    }

    public void updateNote(Note note) {
        Log.d(TAG, "updateNote: " + note.toString());
        new UpdateNoteAsync(noteDao).execute(note);
    }

    public void deleteNote(Note note) {
        new DeleteNoteAsync(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsync(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public static class InsertNoteAsync extends AsyncTask<Note, Void, Void> {

        NoteDao noteDao;

        InsertNoteAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insetNote(notes[0]);
            return null;
        }
    }

    public static class UpdateNoteAsync extends AsyncTask<Note, Void, Void> {

        NoteDao noteDao;

        UpdateNoteAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.updateNote(notes[0]);
            return null;
        }
    }

    public static class DeleteNoteAsync extends AsyncTask<Note, Void, Void> {

        NoteDao noteDao;

        DeleteNoteAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteNote(notes[0]);
            return null;
        }
    }

    public static class DeleteAllNotesAsync extends AsyncTask<Void, Void, Void> {

        NoteDao noteDao;

        DeleteAllNotesAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
