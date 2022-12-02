package com.example.semester1.containers;

import com.example.semester1.ResourceLoader;
import com.example.semester1.core.Room;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Node;

import java.util.ArrayList;


/*
 * Class to contain a view of the current room
 */

public class RoomView extends AnchorPane {

    private GridPane grid = new GridPane();

    private ArrayList<Node> itemNodes = new ArrayList<>();
    private ArrayList<Node> appliancesNodes = new ArrayList<>();

    private Label roomName;


    public RoomView() {
        this.getChildren().add(this.grid);
        this.setAnchorsOnGridPane(0);


        Color color = Color.BLACK;
        BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY , BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);
        this.setBorder(border);
    }

    public void setRoom(Room room) {
        this.setBackground(room);
        this.getChildren().remove(roomName);
        this.roomName = new Label(room.getDisplayName());
        this.roomName.setTextFill(Color.YELLOW);
        this.getChildren().add(roomName);
    }


    private void setAnchorsOnGridPane(double value) {
        AnchorPane.setTopAnchor(this.grid, value);
        AnchorPane.setRightAnchor(this.grid, value);
        AnchorPane.setBottomAnchor(this.grid, value);
        AnchorPane.setLeftAnchor(this.grid, value);
    }


    private void setBackground(Room room) {
        Image image = new Image(ResourceLoader.loadGameAssetAsInputStream("rooms", room.getId()));

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, true));
        Background background = new Background(backgroundImage);

        this.setBackground(background);
    }
}