package com.suffixit.stickynote.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int noteCategoryId;
    private int noteBackgroundColor;
    private long noteCreatedAt;

    public Note(String title, String description, int noteCategoryId, int noteBackgroundColor, long noteCreatedAt) {
        this.title = title;
        this.description = description;
        this.noteCategoryId = noteCategoryId;
        this.noteBackgroundColor = noteBackgroundColor;
        this.noteCreatedAt = noteCreatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoteCategoryId() {
        return noteCategoryId;
    }

    public void setNoteCategoryId(int noteCategoryId) {
        this.noteCategoryId = noteCategoryId;
    }

    public int getNoteBackgroundColor() {
        return noteBackgroundColor;
    }

    public void setNoteBackgroundColor(int noteBackgroundColor) {
        this.noteBackgroundColor = noteBackgroundColor;
    }

    public long getNoteCreatedAt() {
        return noteCreatedAt;
    }

    public void setNoteCreatedAt(long noteCreatedAt) {
        this.noteCreatedAt = noteCreatedAt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", noteCategoryId=" + noteCategoryId +
                ", noteBackgroundColor=" + noteBackgroundColor +
                ", noteCreatedAt=" + noteCreatedAt +
                '}';
    }
}
