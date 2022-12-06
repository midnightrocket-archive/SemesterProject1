package com.example.semester1;

import com.example.semester1.containers.RoomNavigationContainer;
import com.example.semester1.core.Classes.Activity;
import com.example.semester1.core.Classes.ActivityList;
import com.example.semester1.core.Classes.Inventory;
import com.example.semester1.core.Classes.Item;
import com.example.semester1.core.Command;
import com.example.semester1.core.Game;
import com.example.semester1.events.GameEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    // FXML variables
    @FXML
    GridPane gamePane;

    @FXML
    Label dayLabel;

    @FXML
    Label pointsLabel;

    @FXML
    Label powerLabel;

    @FXML
    private AnchorPane roomOuterAnchorPane;

    @FXML
    ListView<String> activitiesList1 = new ListView<>();

    @FXML
    ListView<String> activitiesList2 = new ListView<String>();

    @FXML
    Label consoleArea;

    @FXML
    HBox inventoryHBox;


    // Normal variables
    Game game;

    private RoomNavigationContainer roomNavigationContainer;

    ObservableList<String> currentActivities1 = FXCollections.observableArrayList();

    ObservableList<String> currentActivities2 = FXCollections.observableArrayList();


    // FXML initialize
    @FXML
    public void initialize() throws IOException {
        // DEBUG:
        dayLabel.setOnMouseClicked(dayTester);


        // Creates an instance of Game
        try {
            game = Game.createInstance("Insert player name here");
        } catch (IllegalStateException e) {
            game = Game.getInstance();
        }


        // Related to activities:
        // Sets both activitiesList's to "observe" currentActivities, and display all of its items.
        activitiesList1.setItems(currentActivities1);
        activitiesList2.setItems(currentActivities2);
        activitiesList1.setFocusTraversable(false);
        activitiesList2.setFocusTraversable(false);


        // Setup for the roomNavigation:
        this.roomNavigationContainer = new RoomNavigationContainer();
        this.roomOuterAnchorPane.getChildren().add(this.roomNavigationContainer);


        this.roomNavigationContainer.setApplianceTooltipGenerator(appliance -> {
            Activity activity = game.getActivityManager().getAllActivities().getByAlias(appliance.getActivityId());
            return new Tooltip(String.format("%s\n Kræver: %d", appliance.getDisplayName(), activity.getPowerCost()));
        });



        this.roomNavigationContainer.setRoom(game.getCurrentRoom());
        this.roomNavigationContainer.addEventHandler(GameEvent.CHANGE_ROOM, event -> {
            game.goRoom(event.getCommand());
            this.roomNavigationContainer.setRoom(game.getCurrentRoom());
        });


        // Event handlers from roomNavigationContainer:
        this.roomNavigationContainer.addEventHandler(GameEvent.USE_APPLIANCE, event -> { // Event handler for using an appliance
            String applianceId = event.getValue();

            // get command to pass to game.useAppliance method
            Command command = event.getCommand();

            if (Objects.equals(applianceId, "bed")) {
                writeInConsoleArea("Ending day");

                try {
                    this.goToBed();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (game.useCommand(command)) {
                    writeInConsoleArea("Task completed: " + game.getActivityManager().getAllActivities().getByAlias(game.getCurrentRoom().getAppliance(applianceId).getActivityId()).getDisplayName());

                    // Updates everything on the GUI
                    this.updateAll();
                } else {
                    writeInConsoleArea("You can't do that");
                }
            }
        });

        this.roomNavigationContainer.addEventHandler(GameEvent.PICKUP_ITEM, event -> { // Event handler for picking up an item
            String itemId = event.getValue();

            // Get command to pass to game.pickupItem method
            Command command = event.getCommand();

            if (game.pickupItem(command)) {
                writeInConsoleArea("Picked up: " + game.getPlayer().getItem(itemId).getDisplayName());

                // Updates everything on the GUI
                this.updateAll();
            } else {
                writeInConsoleArea("You can't do that");
            }
        });


        // Updates the user interface:
        this.updateAll();
    }


    // Update methods
    public void updateAll() {
        this.updatePower();
        this.updateDay();
        this.updatePoints();
        this.updateActivities();
        this.roomNavigationContainer.updateView();
        this.updateInventory();
    }

    public void updatePower() {
        powerLabel.setText("Current Power: " + game.getPower());
    }

    public void updateDay() {
        dayLabel.setText("Day Number: " + game.getDay());
    }

    public void updatePoints() {
        pointsLabel.setText("Current points: " + game.getPoints());
    }

    public void updateActivities() { // Method to show current activities: Should be updated everytime a new day begins OR an activity has been completed.
        activitiesList1.getItems().clear();
        activitiesList2.getItems().clear();

        // Daily:
        ActivityList missingDailyActivities = game.getActivityManager().getMissingDailyActivities();
        for (int i = 0; i < missingDailyActivities.size(); i++) {
            currentActivities1.add(missingDailyActivities.get(i).getDisplayName());
        }

        // Extra:
        ActivityList missingExtraActivities = game.getActivityManager().getMissingNoneDailyActivities();
        for (int i = 0; i < missingExtraActivities.size(); i++) {
            currentActivities2.add(missingExtraActivities.get(i).getDisplayName());
        }
    }

    public void updateInventory() {
        // Deletes all items in the visual inventory
        inventoryHBox.getChildren().clear();

        // Gets all items in the inventory
        Inventory inventory = game.getPlayer().getInventory();

        // Try-catch statement, for loading a file
        try {
            for (Item item : inventory) {
                // Gets the image and loads it
                Image image = new Image(ResourceLoader.loadGameAssetAsInputStream("items", item.getId()));

                // Creates a imageView and a stackPane
                ImageView imageView = new ImageView(image);
                StackPane stackPane = new StackPane();

                Tooltip tooltip = new Tooltip(item.getDisplayName());
                Tooltip.install(imageView, tooltip);

                // Setting up the imageView
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);

                // Setting up the stackPane
                stackPane.setPrefSize(50, 50);
                stackPane.setMaxHeight(50);
                stackPane.setMaxWidth(50);
                stackPane.setAlignment(Pos.TOP_LEFT);
                stackPane.setStyle("-fx-border-color:#11111199; -fx-border-width: 2 2 2 2; -fx-border-style: solid; -fx-border-insets: 0 0 0 10");

                // Adds the imageView to the stackPane
                stackPane.getChildren().add(imageView);

                // Adds the stackPane to the visual inventory
                inventoryHBox.getChildren().add(stackPane);
            }
        } catch (Exception e) {
            // Message if an exception happens in the try area
            System.out.println(e.getMessage() + " | " + e.getClass());
        }
    }


    // Generel methods
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
                    existingTextArray[i] = existingTextArray[i + 1];
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


    public void goToBed() throws IOException {
        GridPane sleepGridPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(("Sleep.fxml"))));
        gamePane.getChildren().setAll(sleepGridPane);
    }




    // DEBUG EVENT HANDLER
    EventHandler<MouseEvent> dayTester = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            game.sleepCommand();
            updateAll();
        }
    };
}