package com.example.semester1.containers;


import com.example.semester1.core.Room;
import com.example.semester1.core.Utilities.Direction;
import com.example.semester1.events.GameEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*
 * Class to contain room view and buttons to change to other rooms.
 */

public class RoomNavigationContainer extends GridPane {

    private static Node getArrowNode(Direction direction) {
        // Just using rectangle as placeholder
        Node node = new Rectangle(10,20);

        // ordinal method is an enum default, which returns the ordinal number of the enum constant
        node.setRotate(90 * direction.ordinal());

        return node;
    }

    private RoomView roomView;

    private ArrayList<Node> exitButtonNodes = new ArrayList<>();

    public RoomNavigationContainer() {



        this.roomView = new RoomView();
        this.add(this.roomView, 1, 1);


        this.setGridLinesVisible(false);

        Color color = Color.GREEN;
        BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.DOTTED,CornerRadii.EMPTY ,BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);
        this.setBorder(border);




        this.setGridConstraints(25);
        this.setAnchors(0);

        this.setExitButton(Direction.NORTH, "north");
        this.setExitButton(Direction.EAST, "east");
        this.setExitButton(Direction.SOUTH, "south");
        this.setExitButton(Direction.WEST, "west");
    }



    private void setAnchors(double value) {
        AnchorPane.setTopAnchor(this, value);
        AnchorPane.setRightAnchor(this, value);
        AnchorPane.setBottomAnchor(this, value);
        AnchorPane.setLeftAnchor(this, value);
    }
    private void setGridConstraints(int size) {
        ColumnConstraints outerColConstraints = new ColumnConstraints(size);
        ColumnConstraints innerColConstraints = new ColumnConstraints();
        innerColConstraints.setHgrow(Priority.ALWAYS);

        List<ColumnConstraints> colConstraints = this.getColumnConstraints();

        colConstraints.add(outerColConstraints);
        colConstraints.add(innerColConstraints);
        colConstraints.add(outerColConstraints);



        RowConstraints outerRowConstraints = new RowConstraints(size);
        RowConstraints innerRowConstraints = new RowConstraints();
        innerRowConstraints.setVgrow(Priority.ALWAYS);

        List<RowConstraints> rowConstraints = this.getRowConstraints();

        rowConstraints.add(outerRowConstraints);
        rowConstraints.add(innerRowConstraints);
        rowConstraints.add(outerRowConstraints);
    }



    private void setExitButton(Direction direction, String roomName) {
        Node node = RoomNavigationContainer.getArrowNode(direction);

        //Add node object to exitButtonNodes, so they can be removed on room change.
        this.exitButtonNodes.add(node);

        switch (direction) {
            case NORTH -> this.add(node, 1, 0);
            case EAST -> this.add(node, 2, 1);
            case SOUTH -> this.add(node, 1, 2);
            case WEST -> this.add(node, 0, 1);
        }

        Tooltip tooltip = new Tooltip(roomName);
        Tooltip.install(node, tooltip);

        GridPane.setHalignment(node, HPos.CENTER);
        GridPane.setValignment(node, VPos.CENTER);

        node.addEventHandler(MouseEvent.MOUSE_CLICKED, (event)-> {
            GameEvent gameEvent = new GameEvent(GameEvent.CHANGE_ROOM);
            gameEvent.setValue(direction.toString());
            node.fireEvent(gameEvent);
        });
    }

    private void clearExitButtons() {
        List<Node> children = this.getChildren();
        for (Node node : this.exitButtonNodes) {
            children.remove(node);
        }
    }


    public void setRoom(Room room) {
        this.roomView.setRoom(room);
        this.clearExitButtons();
        for (Map.Entry<Direction, Room> exit : room.getAllExits().entrySet()) {
            this.setExitButton(exit.getKey(), exit.getValue().getDisplayName());
        }
    }

    public void updateView() {
        this.roomView.updateView();
    }
}