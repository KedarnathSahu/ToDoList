package com.cumulations.todolist.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //to turn this class to singleton
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if(instance==null){
            instance=Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
