package com.notesapp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class MainView {
    private Parent view;

    public MainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent getView() {
        return view;
    }
}