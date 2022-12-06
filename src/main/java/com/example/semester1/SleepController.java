package com.example.semester1;

import com.example.semester1.core.Classes.Activity;
import com.example.semester1.core.Classes.ActivityList;
import com.example.semester1.core.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

public class SleepController {
    @FXML
    GridPane sleepPane;

    @FXML
    Label dayHeader;

    @FXML
    Label points;

    @FXML
    Label powerLeft;

    @FXML
    ListView activitiesDone;

    @FXML
    ListView activitiesMissed;

    @FXML
    Button nextDayButton;

    @FXML
    Label lastDayText;


    Game game;

    ObservableList<String> activitiesDoneObservableList = FXCollections.observableArrayList();

    ObservableList<String> activitiesMissedObservableList = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        try {
            game = Game.getInstance();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (game.getDay() == 7) {
            nextDayButton.setVisible(false);
        } else {
            lastDayText.setVisible(false);
        }

        activitiesDone.setItems(activitiesDoneObservableList);
        activitiesMissed.setItems(activitiesMissedObservableList);
        activitiesDone.setFocusTraversable(false);
        activitiesMissed.setFocusTraversable(false);

        setAllData();
    }


    // Update methods
    public void setAllData() {
        //
        // Insert code for Daily activities done here
        //

        ActivityList missingDailyActivities = game.getActivityManager().getMissingDailyActivities();
        if (game.getDay() == 7) {
            missingDailyActivities.addAll(game.getActivityManager().getMissingNoneDailyActivities());
        }
        for (Activity missingDailyActivity : missingDailyActivities) {
            activitiesMissedObservableList.add(missingDailyActivity.getDisplayName());
        }

        this.powerLeft.setText("Power tilovers: " + game.getPower());
        this.dayHeader.setText("Dag " + game.getDay() + " klaret");

        game.sleepCommand();

        this.points.setText("Point ialt: " + game.getPoints());
    }


    @FXML
    public void startNextDay() throws IOException {
        GridPane gameGridPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(("Game.fxml"))));
        sleepPane.getChildren().setAll(gameGridPane);
    }
}