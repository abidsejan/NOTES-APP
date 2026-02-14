package com.notesapp.view;

import com.notesapp.model.Note;
import com.notesapp.util.DateUtil;
import javafx.scene.control.ListCell;

public class NoteCellFactory extends ListCell<Note> {

    @Override
    protected void updateItem(Note note, boolean empty) {
        super.updateItem(note, empty);

        if (empty || note == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(note.getTitle() + "\n" + DateUtil.format(note.getLastModified()));
        }
    }
}