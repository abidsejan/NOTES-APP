package com.notesapp.controller;

import com.notesapp.model.Note;
import com.notesapp.model.NotesModel;
import com.notesapp.service.NoteService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class NotesController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<Note> notesListView;

    @FXML
    private HTMLEditor noteEditorView;

    private NoteService noteService;
    private NotesModel notesModel;

    public NotesController() {
        this.noteService = new NoteService();
        this.notesModel = new NotesModel();
    }

    @FXML
    public void initialize() {
        notesModel.getNotes().addAll(noteService.getNotes());
        notesListView.setItems(notesModel.getNotes());

        notesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                noteEditorView.setHtmlText(newValue.getContent());
            }
        });

        noteEditorView.setOnKeyReleased(event -> {
            Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
            if (selectedNote != null) {
                selectedNote.setContent(noteEditorView.getHtmlText());
                noteService.saveNote(selectedNote);
            }
        });
    }

    @FXML
    private void newNote() {
        TextInputDialog dialog = new TextInputDialog("New Note");
        dialog.setTitle("New Note");
        dialog.setHeaderText("Enter a title for your new note:");
        dialog.setContentText("Title:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(title -> {
            Note newNote = new Note(title, "");
            notesModel.getNotes().add(newNote);
            noteService.saveNote(newNote);
            notesListView.getSelectionModel().select(newNote);
        });
    }

    @FXML
    private void deleteNote() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            notesModel.getNotes().remove(selectedNote);
            noteService.deleteNote(selectedNote);
        }
    }

    @FXML
    private void openRecycleBin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/notesapp/view/RecycleBinView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Recycle Bin");
            stage.setScene(new Scene(root));
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();
            notesModel.getNotes().setAll(noteService.getNotes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}