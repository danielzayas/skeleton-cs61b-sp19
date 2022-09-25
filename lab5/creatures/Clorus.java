package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;

import static huglife.HugLifeUtils.*;

public class Clorus extends Creature {
    // Class attributes
    private static final String NAME = "clorus";
    private static final double moveEnergy = 0.3;
    private static final double stayEnergy = 0.1;
    private static int r = 34;
    private static int g = 0;
    private static int b = 231;

    /**
     * Creates a Clorus with energy e.
     * @param e
     */
    public Clorus(double e) {
        super(NAME);
        energy = e;
    }

    public Clorus() {
        this(1.0);
    }

    @Override
    public void move() {
        energy -= moveEnergy;
        energy = Math.max(0, energy);
    }

    @Override
    public void attack(Creature c) {
        double cEnergy = c.energy();
        c.setEnergy(0.0);  // TODO: try excluding this?
        this.setEnergy(energy + cEnergy);
    }

    /**
     * TODO: How to have a method in the super class Creature that returns
     * instances of the appropriate subclasses Plip and Clorus.
     * Cloruses and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    @Override
    public Clorus replicate() {
        double postReplicateEnergy = energy / 4.0;
        energy = postReplicateEnergy;

        return new Clorus(postReplicateEnergy);
    }

    @Override
    public void stay() {
        energy -= stayEnergy;
        energy = Math.max(0, energy);
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // process neighbors
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            Direction direction = entry.getKey();
            Occupant occupant = entry.getValue();

            // TODO: OccupantType enum?
            if (Objects.equals(occupant.name(), Plip.NAME)) {
                plipNeighbors.addLast(direction);
            } else if (Objects.equals(occupant.name(), Empty.NAME)) {
                emptyNeighbors.addLast(direction);
            }
        }

        // If there are no empty squares, the Clorus will STAY
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
        if (!plipNeighbors.isEmpty()) {
            Direction plipDirection = (Direction) randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, plipDirection);
        }

        // Otherwise, if the Clorus has energy greater than or equal to one, it will
        // REPLICATE to a random empty square.
        Direction emptyDirection = (Direction) randomEntry(emptyNeighbors);
        if (energy >= 1.15) {
            return new Action(Action.ActionType.REPLICATE, emptyDirection);
        } else {
            // Otherwise, the Clorus will MOVE to a random empty square.
            return new Action(Action.ActionType.MOVE, emptyDirection);
        }
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }
}
