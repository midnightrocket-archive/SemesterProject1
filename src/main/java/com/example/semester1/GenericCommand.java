package com.example.semester1;

import com.example.semester1.core.Action;
import com.example.semester1.core.Command;

/*
 * GenericCommand class for use by event handler, to pass values to game methods.
 */
public class GenericCommand implements Command {

    private String value;
    public GenericCommand(String value) {
        this.value = value;
    }

    public GenericCommand() {
        this(null);
    }

    @Override
    public Action getAction() {
        return null;
    }

    @Override
    public String getCommandValue() {
        return this.value;
    }

    @Override
    public boolean hasCommandValue() {
        return this.getCommandValue() != null;
    }
}