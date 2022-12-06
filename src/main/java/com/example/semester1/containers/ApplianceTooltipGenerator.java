package com.example.semester1.containers;

import com.example.semester1.core.Classes.Appliance;
import javafx.scene.control.Tooltip;

public interface ApplianceTooltipGenerator {
    Tooltip generate(Appliance appliance);
}