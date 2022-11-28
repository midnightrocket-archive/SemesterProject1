package com.example.semester1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Semesterprojekt.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) throws IOException {
        /*
        try {
            CommandLineClient client = new CommandLineClient();
            client.play();
        } catch (IOException exception) {
            System.out.println("Cannot load configs exiting.");
            throw exception;
        }
         */

        launch();
    }
}