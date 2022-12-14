package creatures;

import huglife.*;

import javax.print.attribute.standard.MediaSize;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;

import static huglife.HugLifeUtils.randomEntry;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {
    protected static final String NAME = "plip";
    private static final double maxEnergy = 2.0;
    private static final double moveEnergy = 0.15;
    private static final double stayEnergy = 0.2;

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super(NAME);
        r = 99;
        // g undefined. See color().
        b = 76;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = (int) Math.round(63 + (energy / maxEnergy * (255 - 63)));
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= moveEnergy;
        energy = Math.max(0, energy);
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += stayEnergy;
        energy = Math.min(maxEnergy, energy);
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        double postReplicateEnergy = energy / 2.0;
        energy = postReplicateEnergy;

        return new Plip(postReplicateEnergy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            String occupantName = entry.getValue().name();

            if (Objects.equals(occupantName, "empty")) {
                // TODO: Occupant implements Comparable?
                emptyNeighbors.addLast(entry.getKey());
            } else if (Objects.equals(occupantName, "clorus")) {
                anyClorus = true;
            }
        }

        if (emptyNeighbors.isEmpty()) { // FIXME
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if (energy >= 1) {
            Direction direction = (Direction) randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, direction);
        }

        // Rule 3
        boolean isMove = Math.random() < 0.5;
        if (anyClorus && isMove) {
            Direction direction = (Direction) randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.MOVE, direction);
        }

        // Rule 4
        return new Action(Action.ActionType.STAY);
    }
}
