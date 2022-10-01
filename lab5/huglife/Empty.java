package huglife;

import java.awt.Color;

public class Empty extends Occupant {
    public static String NAME = "empty";

    public Empty() {
        super(NAME);
    }

    /**
     * Returns hardcoded black
     */
    public Color color() {
        return color(255, 255, 255);
    }
}