package com.notesapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.notesapp.model.Note;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteService {

    private static final String NOTES_FILE = "notes.json";
    private static final String DELETED_NOTES_FILE = "deleted_notes.json";
    private final ObjectMapper objectMapper;
    private List<Note> notes;
    private List<Note> deletedNotes;

    public NoteService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.notes = loadNotes(NOTES_FILE);
        this.deletedNotes = loadNotes(DELETED_NOTES_FILE);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<Note> getDeletedNotes() {
        return deletedNotes;
    }

    public void saveNote(Note note) {
        notes.removeIf(n -> n.getId().equals(note.getId()));
        notes.add(note);
        saveNotes(NOTES_FILE, notes);
    }

    public void deleteNote(Note note) {
        System.out.println("Deleting note: " + note.getTitle());
        notes.removeIf(n -> n.getId().equals(note.getId()));
        deletedNotes.add(note);
        saveNotes(NOTES_FILE, notes);
        saveNotes(DELETED_NOTES_FILE, deletedNotes);
        System.out.println("Note added to deleted notes list. Total deleted notes: " + deletedNotes.size());
    }

    public void restoreNote(Note note) {
        deletedNotes.removeIf(n -> n.getId().equals(note.getId()));
        notes.add(note);
        saveNotes(DELETED_NOTES_FILE, deletedNotes);
        saveNotes(NOTES_FILE, notes);
    }

    public void permanentlyDeleteNote(Note note) {
        deletedNotes.removeIf(n -> n.getId().equals(note.getId()));
        saveNotes(DELETED_NOTES_FILE, deletedNotes);
    }

    private List<Note> loadNotes(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Note>>() {});
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveNotes(String fileName, List<Note> notes) {
        try {
            System.out.println("Saving " + notes.size() + " notes to " + fileName);
            objectMapper.writeValue(new File(fileName), notes);
            System.out.println("Successfully saved notes to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}