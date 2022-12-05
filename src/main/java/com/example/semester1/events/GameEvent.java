package com.example.semester1.events;

import com.example.semester1.GenericCommand;
import com.example.semester1.core.Command;
import javafx.event.Event;
import javafx.event.EventType;

/*
 * Custom GameEvent which handles custom game events.
 */

public class GameEvent extends Event {
    // Create new event type for GameEvent. EventType
    public static final EventType<GameEvent> ANY = new EventType<>(Event.ANY, "GAME_EVENT");
    public static final EventType<GameEvent> CHANGE_ROOM = new EventType<>(GameEvent.ANY, "CHANGE_ROOM");
    public static final EventType<GameEvent> USE_APPLIANCE = new EventType<>(GameEvent.ANY, "USE_APPLIANCE");
    public static final EventType<GameEvent> PICKUP_ITEM = new EventType<>(GameEvent.ANY, "PICKUP_ITEM");

    private String value;
    public GameEvent(EventType<? extends GameEvent> eventType) {
        super(eventType);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Command getCommand() {
        return new GenericCommand(this.getValue());
    }
}