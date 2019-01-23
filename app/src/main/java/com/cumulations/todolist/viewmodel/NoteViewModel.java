package com.cumulations.todolist.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.cumulations.todolist.model.model.Note;
import com.cumulations.todolist.model.repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allLocalNotes;
    private LiveData<List<Note>> allRemoteNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allRemoteNotes=repository.getRemoteAllNotes();
        allLocalNotes=repository.getLocalAllNotes();
    }

    public void insert(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllLocalNotes() {
        return allLocalNotes;
    }

    public LiveData<List<Note>> getAllRemoteNotes() {
        return allRemoteNotes;
    }
}
