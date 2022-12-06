package com.example.semester1.containers;

import com.example.semester1.ResourceLoader;
import com.example.semester1.TooltipFactory;
import com.example.semester1.core.Classes.Appliance;
import com.example.semester1.core.Classes.Item;
import com.example.semester1.core.Room;
import com.example.semester1.events.GameEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;


/*
 * Class to contain a view of the current room
 */

public class RoomView extends Pane {

    //Arraylist to save item and appliance nodes, in order to later be able to remove them from view.
    private ArrayList<Node> itemNodes = new ArrayList<>();
    private ArrayList<Node> appliancesNodes = new ArrayList<>();

    private Label roomNameLabel;

    private Room room;

    private ApplianceTooltipGenerator applianceTooltipGenerator = new ApplianceTooltipGenerator() {
        @Override
        public Tooltip generate(Appliance appliance) {
            return new Tooltip(appliance.getDisplayName());
        }
    };

    private static Node createNode(String subdir, Item object) {
        Image image = new Image(ResourceLoader.loadGameAssetAsInputStream(subdir, object.getId()));

        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);

        imageView.setX(object.getX());
        imageView.setY(object.getY());

        return imageView;
    }


    private static Node createItemNode(Item item) {
        Node node = RoomView.createNode("items", item);
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            GameEvent gameEvent = new GameEvent(GameEvent.PICKUP_ITEM);
            gameEvent.setValue(item.getId());
            node.fireEvent(gameEvent);
        });

        Tooltip tooltip = TooltipFactory.create(item.getDisplayName());
        Tooltip.install(node, tooltip);

        return node;
    }


    public RoomView() {
        Color color = Color.BLACK;
        BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);
        this.setBorder(border);
    }


    private Node createApplianceNode(Appliance appliance) {
        Node node = RoomView.createNode("appliances", appliance);
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            GameEvent gameEvent = new GameEvent(GameEvent.USE_APPLIANCE);
            gameEvent.setValue(appliance.getId());
            node.fireEvent(gameEvent);
        });


        Tooltip tooltip = this.applianceTooltipGenerator.generate(appliance);
        Tooltip.install(node, tooltip);

        return node;
    }

    public void setApplianceTooltipGenerator(ApplianceTooltipGenerator generator) {
        this.applianceTooltipGenerator = generator;
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
            this.getChildren().remove(node);
        }
    }

    private void addAppliances() {
        for (Appliance appliance : this.room.getAllAppliances()) {
            Node node = this.createApplianceNode(appliance);
            this.appliancesNodes.add(node);
            this.getChildren().add(node);
        }
    }

    private void clearItemNodes() {
        for (Node node : this.itemNodes) {
            this.getChildren().remove(node);
        }
    }

    private void addItems() {
        for (Item item : this.room.getAllItems()) {
            Node node = RoomView.createItemNode(item);
            this.itemNodes.add(node);
            this.getChildren().add(node);
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