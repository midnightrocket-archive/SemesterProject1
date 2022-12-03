package com.example.semester1.containers;

import com.example.semester1.ResourceLoader;
import com.example.semester1.core.Classes.AliasableImplementation;
import com.example.semester1.core.Classes.Appliance;
import com.example.semester1.core.Classes.Item;
import com.example.semester1.core.Room;
import com.example.semester1.events.GameEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    private Label roomNameLabel;

    private Room room;


    private static Node createNode(String subdir, AliasableImplementation object) {
        Image image = new Image(ResourceLoader.loadGameAssetAsInputStream(subdir, object.getId()));
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.maxWidth(30);
        imageView.setFitWidth(100);

        Tooltip tooltip = new Tooltip(object.getDisplayName());
        Tooltip.install(imageView, tooltip);
        return imageView;
    }

    private static Node createApplianceNode(Appliance appliance) {
        Node node = RoomView.createNode("appliances", appliance);
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            GameEvent gameEvent = new GameEvent(GameEvent.USE_APPLIANCE);
            gameEvent.setValue(appliance.getId());
            node.fireEvent(gameEvent);
        });
        return node;
    }

    private static Node createItemNode(Item item) {
        Node node = RoomView.createNode("items", item);
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            GameEvent gameEvent = new GameEvent(GameEvent.PICKUP_ITEM);
            gameEvent.setValue(item.getId());
            node.fireEvent(gameEvent);
        });
        return node;
    }


    public RoomView() {
        this.getChildren().add(this.grid);
        this.setAnchorsOnGridPane(1);

        Color color = Color.BLACK;
        BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY , BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);
        this.setBorder(border);
    }

    private void setAnchorsOnGridPane(double value) {
        AnchorPane.setTopAnchor(this.grid, value);
        AnchorPane.setRightAnchor(this.grid, value);
        AnchorPane.setBottomAnchor(this.grid, value);
        AnchorPane.setLeftAnchor(this.grid, value);
    }

    public void setRoom(Room room) {
        this.room = room;
        this.updateView();
    }

    public void updateView() {
        this.setBackground();
        this.clearNodes();
        this.addAppliances();
        this.addItems();

        this.roomNameLabel = new Label(this.room.getDisplayName());
        this.roomNameLabel.setTextFill(Color.YELLOW);

        this.getChildren().add(roomNameLabel);
    }

    private void clearNodes() {
        this.getChildren().remove(this.roomNameLabel);
        this.clearAppliancesNodes();
        this.clearItemNodes();
    }

    private void clearAppliancesNodes() {
        for (Node node : this.appliancesNodes) {
            this.grid.getChildren().remove(node);
        }
    }

    private void addAppliances() {
        for (Appliance appliance : this.room.getAllAppliances()) {
            Node node = RoomView.createApplianceNode(appliance);
            this.appliancesNodes.add(node);
            this.grid.addRow(0, node);
        }
    }

    private void clearItemNodes() {
        for (Node node : this.itemNodes) {
            this.grid.getChildren().remove(node);
        }
    }

    private void addItems() {
        for (Item item : this.room.getAllItems()) {
            Node node = RoomView.createItemNode(item);
            this.itemNodes.add(node);
            this.grid.addRow(1, node);
        }
    }


    private void setBackground() {
        Image image = new Image(ResourceLoader.loadGameAssetAsInputStream("rooms", this.room.getId()));

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, true));
        Background background = new Background(backgroundImage);

        this.setBackground(background);
    }
}