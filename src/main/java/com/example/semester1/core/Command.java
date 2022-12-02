package com.example.semester1.core;

public interface Command {

    Action getAction();

    String getCommandValue();

    boolean hasCommandValue();
}