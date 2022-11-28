package com.example.semester1;

import com.example.semester1.core.Classes.ActivityContainer;
import com.example.semester1.core.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainController {

    Game game;

    @FXML
    public void initialize() throws IOException {
        powerLabel.setOnMouseClicked(writeInConsoleAreaTester);
        writeInConsoleArea("Hello World!");

        game = Game.createInstance("Player name");

        // Related to activities:
        // Sets activitiesList to "observe" currentActivities, and display all of its items.
        activitiesList.setItems(currentActivities);
        activitiesList.setFocusTraversable(false);
        this.updateActivities();
        this.updateActivities();

        this.updateDay();
        this.updatePower();
        this.updatePoints();
    }
    
    // Activities
    ObservableList<String> currentActivities = FXCollections.observableArrayList();

    @FXML ListView activitiesList = new ListView();

    @FXML
    Label activities;

    //Stats declare
    @FXML
    Label dayLabel;
    
    @FXML
    Label pointsLabel;
    
    @FXML
    Label powerLabel;

    //Stats methods

    public void updatePower() {
        powerLabel.setText("Current Power: " + game.getPower());
    }

    public void updateDay() {
        dayLabel.setText("Day Number: " + game.getDay());
    }

    public void updatePoints() {
        pointsLabel.setText("Current points: " + game.getPoints());
    }

    // Day overlay
    //@FXML


    // Console area
    @FXML
    Label consoleArea;

    static int i;


    EventHandler<MouseEvent> writeInConsoleAreaTester = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            i++;
            writeInConsoleArea("POWER " + i);
        }
    };


    public void writeInConsoleArea(String text) {
        String existingText = consoleArea.getText();

        String[] existingTextArray = new String[5];
        String[] existingTextArraySplit = existingText.split("\n");

        for (int i = 0; (i < existingTextArraySplit.length); i++) {
            if (!existingTextArraySplit[i].isEmpty()) {
                existingTextArray[i] = existingTextArraySplit[i];
            }
            if (i == 4) {
                break;
            }
        }

        int _length = 0;
        for (String string : existingTextArray) {
            if (string != null) {
                _length++;
            }
        }

        if (_length < 5) {
            for (int i = 0; i < existingTextArray.length; i++) {
                if (existingTextArray[i] == null) {
                    existingTextArray[i] = text + "\n";
                    break;
                }
            }
        } else {
            for (int i = 0; i < (existingTextArray.length); i++) {
                if (i == 4) {
                    existingTextArray[i] = text + "\n";
                } else {
                    existingTextArray[i] = existingTextArray[i+1];
                }
            }
        }

        StringBuilder output = new StringBuilder();

        for (String string : existingTextArray) {
            if (string != null) {
                output.append(string).append("\n");
            } else {
                output.append(" \n");
            }
        }

        consoleArea.setText(output.toString());
    }
    
    // Method to show current activities: Should be updated everytime a new day begins OR an activity has been completed.
    public void updateActivities() {
        currentActivities.removeAll(); // Clears Observable List

        // Daily:
        ActivityContainer missingDailyActivities = game.getActivityManager().getMissingDailyActivities();
        for (int i = 0; i < missingDailyActivities.size(); i++) {
            currentActivities.add(missingDailyActivities.get(i).getDisplayName());
        }

        // Extra:
        ActivityContainer missingExtraActivities = game.getActivityManager().getMissingNoneDailyActivities();
        for (int i = 0; i < missingExtraActivities.size(); i++) {
            currentActivities.add(missingExtraActivities.get(i).getDisplayName());
        }
    }
}
