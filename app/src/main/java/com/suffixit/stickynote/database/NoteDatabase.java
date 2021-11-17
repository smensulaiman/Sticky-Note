package com.suffixit.stickynote.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.suffixit.stickynote.R;
import com.suffixit.stickynote.model.CategoryModel;
import com.suffixit.stickynote.model.Note;
import com.suffixit.stickynote.repository.CategoryDao;
import com.suffixit.stickynote.repository.NoteDao;

@Database(entities = {Note.class, CategoryModel.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static Context myContext;

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public abstract CategoryDao categoryDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        myContext = context;
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsync(instance).execute();
        }
    };

    public static class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;
        private CategoryDao categoryDao;

        public PopulateDatabaseAsync(NoteDatabase noteDatabase) {
            noteDao = noteDatabase.noteDao();
            categoryDao = noteDatabase.categoryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insetNote(new Note(myContext.getString(R.string.lorem_ipsum_title), myContext.getString(R.string.lorem_ipsum_description), 1, R.color.color_green_light, System.currentTimeMillis()));
            noteDao.insetNote(new Note(myContext.getString(R.string.lorem_ipsum_title), myContext.getString(R.string.lorem_ipsum_description), 2, R.color.color_yellow_light, System.currentTimeMillis()));
            noteDao.insetNote(new Note(myContext.getString(R.string.lorem_ipsum_title), myContext.getString(R.string.lorem_ipsum_description), 3, R.color.color_pink_light, System.currentTimeMillis()));
            noteDao.insetNote(new Note(myContext.getString(R.string.lorem_ipsum_title), myContext.getString(R.string.lorem_ipsum_description), 4, R.color.color_sky_light, System.currentTimeMillis()));
            noteDao.insetNote(new Note(myContext.getString(R.string.lorem_ipsum_title), myContext.getString(R.string.lorem_ipsum_description), 1, R.color.color_red_light, System.currentTimeMillis()));
            noteDao.insetNote(new Note(myContext.getString(R.string.lorem_ipsum_title), myContext.getString(R.string.lorem_ipsum_description), 2, R.color.color_purple_light, System.currentTimeMillis()));

            categoryDao.insetCategory(new CategoryModel("Contact Info", R.drawable.ic_phone, R.color.color_yellow_light));
            categoryDao.insetCategory(new CategoryModel("Password Info", R.drawable.ic_security, R.color.color_green_light));
            categoryDao.insetCategory(new CategoryModel("Add", R.drawable.ic_add, R.color.color_green_light));
            categoryDao.insetCategory(new CategoryModel("Cloud", R.drawable.ic_cloud, R.color.color_green_light));
            return null;
        }
    }

}
