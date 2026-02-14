package com.notesapp;

import com.notesapp.model.Note;
import com.notesapp.service.NoteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotesAppTest {

    private NoteService noteService;
    private static final String NOTES_FILE = "notes.json";

    @BeforeEach
    void setUp() throws IOException {
        System.out.println("Setting up test...");
        Files.deleteIfExists(Paths.get(NOTES_FILE));
        Files.deleteIfExists(Paths.get("deleted_notes.json"));
        noteService = new NoteService();
    }

    @AfterEach
    void tearDown() throws IOException {
        System.out.println("Tearing down test...");
        Files.deleteIfExists(Paths.get(NOTES_FILE));
        Files.deleteIfExists(Paths.get("deleted_notes.json"));
    }

    @Test
    void testSaveAndLoadNotes() {
        Note note1 = new Note("Test Note 1", "This is a test note.");
        Note note2 = new Note("Test Note 2", "This is another test note.");

        noteService.saveNote(note1);
        noteService.saveNote(note2);

        List<Note> loadedNotes = noteService.getNotes();

        assertEquals(2, loadedNotes.size());
        assertTrue(loadedNotes.stream().anyMatch(n -> n.getTitle().equals("Test Note 1")));
        assertTrue(loadedNotes.stream().anyMatch(n -> n.getTitle().equals("Test Note 2")));
    }

    @Test
    void testDeleteNote() {
        Note note1 = new Note("Test Note 1", "This is a test note.");
        Note note2 = new Note("Test Note 2", "This is another test note.");

        noteService.saveNote(note1);
        noteService.saveNote(note2);

        noteService.deleteNote(note1);

        List<Note> loadedNotes = noteService.getNotes();

        assertEquals(1, loadedNotes.size());
        assertFalse(loadedNotes.stream().anyMatch(n -> n.getTitle().equals("Test Note 1")));
        assertTrue(loadedNotes.stream().anyMatch(n -> n.getTitle().equals("Test Note 2")));
    }

    @Test
    void testNotesFileIsCreated() {
        Note note = new Note("Test Note", "This is a test note.");
        noteService.saveNote(note);

        File file = new File(NOTES_FILE);
        assertTrue(file.exists());
    }
}

