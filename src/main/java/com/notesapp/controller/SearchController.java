package com.notesapp.controller;

import com.notesapp.model.Note;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class SearchController {

    private final TextField searchField;
    private final ObservableList<Note> notes;

    public SearchController(TextField searchField, ObservableList<Note> notes) {
        this.searchField = searchField;
        this.notes = notes;
    }

    public void setupSearch() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterNotes(newValue);
        });
    }

    private void filterNotes(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            // Show all notes
        } else {
            // Filter notes based on search text
        }
    }
}
