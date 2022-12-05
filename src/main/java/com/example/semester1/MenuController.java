package com.example.semester1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

public class MenuController {
    @FXML
    GridPane menuPane;

    @FXML
    public void startGame() throws IOException {
        GridPane gameGridPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(("Game.fxml"))));
        menuPane.getChildren().setAll(gameGridPane);
    }
}