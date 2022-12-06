package com.example.semester1;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class TooltipFactory {
    public static Tooltip create(String string) {
        Tooltip tooltip = new Tooltip(string);
        tooltip.setShowDelay(Duration.ZERO);
        return tooltip;
    }
}