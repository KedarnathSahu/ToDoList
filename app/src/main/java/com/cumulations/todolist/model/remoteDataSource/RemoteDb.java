package com.cumulations.todolist.model.remoteDataSource;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cumulations.todolist.model.model.Note;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoteDb {
    private DatabaseReference mDatabaseRef;

    public RemoteDb() {
        this.mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
    }

    public void insert(Note note) {
        String noteId = String.valueOf(note.getId());
        mDatabaseRef.child(noteId).setValue(note);
    }

    public void update(Note note) {
        Log.e("@@@", "U push id:" + note.getId());
        final String noteId = "" + note.getId();
        mDatabaseRef.child("").child(noteId).setValue(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("@@@", "Write was successful!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("@@@", "Write failed!");
                    }
                });
    }

    public void delete(Note note) {
        String noteId = String.valueOf(note.getId());
        mDatabaseRef.child(noteId).removeValue();
    }

    public void deleteAllNotes() {
        mDatabaseRef.setValue(null);
    }

    public void getAllNotes() {

    }
}
