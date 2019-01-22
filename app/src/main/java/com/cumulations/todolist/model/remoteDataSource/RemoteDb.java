package com.cumulations.todolist.model.remoteDataSource;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cumulations.todolist.model.model.Note;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RemoteDb {
    private DatabaseReference mDatabaseRef;

    private List<Note> mNote;

    public RemoteDb() {
        this.mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mNote = new ArrayList<>();
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
                        Log.e("@@@", "Write was successful.");
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

    public  List<Note> getAllNotes() {
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mNote.clear();
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    Note note = noteSnapshot.getValue(Note.class);
                    mNote.add(note);
                }
                Log.e("@@@", "Reading data was successful.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("@@@", databaseError.getMessage());
            }
        });
        return mNote;
    }
}
