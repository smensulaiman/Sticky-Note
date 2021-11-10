package com.suffixit.stickynote.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.suffixit.stickynote.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insetNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY noteCreatedAt DESC")
    LiveData<List<Note>> getAllNotes();

}
