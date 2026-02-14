package com.notesapp.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Note {

    private String id;
    private String title;
    private String content;
    private LocalDateTime lastModified;

    public Note() {
        // Default constructor for Jackson deserialization
    }

    public Note(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.lastModified = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return title;
    }
}