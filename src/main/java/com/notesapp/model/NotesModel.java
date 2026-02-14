package com.notesapp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NotesModel {

    private final ObservableList<Note> notes;

    public NotesModel() {
        this.notes = FXCollections.observableArrayList();
    }

    public ObservableList<Note> getNotes() {
        return notes;
    }
}