package com.example.semester1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Semesterprojekt.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Semesterprojekt");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:src/main/resources/com/example/semester1/placeholderImage.png"));
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