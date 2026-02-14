package com.notesapp.view;

import com.notesapp.model.Note;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class NotesListView extends ListView<Note> {

    public NotesListView() {
        setCellFactory(new Callback<ListView<Note>, ListCell<Note>>() {
            @Override
            public ListCell<Note> call(ListView<Note> param) {
                return new NoteCellFactory();
            }
        });
    }
}