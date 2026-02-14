package com.notesapp.controller;

import com.notesapp.model.Note;
import com.notesapp.service.NoteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class RecycleBinController {

    @FXML
    private ListView<Note> deletedNotesListView;

    private NoteService noteService;
    private ObservableList<Note> deletedNotes;

    public RecycleBinController() {
        this.noteService = new NoteService();
        this.deletedNotes = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        deletedNotes.addAll(noteService.getDeletedNotes());
        deletedNotesListView.setItems(deletedNotes);
    }

    @FXML
    private void restoreNote() {
        Note selectedNote = deletedNotesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            noteService.restoreNote(selectedNote);
            deletedNotes.setAll(noteService.getDeletedNotes());
        }
    }

    @FXML
    private void permanentlyDeleteNote() {
        Note selectedNote = deletedNotesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            noteService.permanentlyDeleteNote(selectedNote);
            deletedNotes.setAll(noteService.getDeletedNotes());
        }
    }
}
