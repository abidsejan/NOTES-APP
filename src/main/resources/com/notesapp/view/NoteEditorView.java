package com.notesapp.view;

import com.notesapp.model.Note;
import javafx.scene.control.TextArea;

public class NoteEditorView extends TextArea {

    private Note currentNote;

    public NoteEditorView() {
        setWrapText(true);
    }

    public void setCurrentNote(Note note) {
        this.currentNote = note;
        if (note != null) {
            setText(note.getContent());
        } else {
            clear();
        }
    }

    public Note getCurrentNote() {
        return currentNote;
    }

    public void updateNoteContent() {
        if (currentNote != null) {
            currentNote.setContent(getText());
        }
    }
}