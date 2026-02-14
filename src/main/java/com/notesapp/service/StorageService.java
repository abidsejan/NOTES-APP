package com.notesapp.service;

import java.io.File;

public class StorageService {

    private static final String NOTES_DIR = "notes";

    public StorageService() {
        new File(NOTES_DIR).mkdirs();
    }

    public File getNotesDir() {
        return new File(NOTES_DIR);
    }
}