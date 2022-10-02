/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import es.datastructur.synthesizer.GuitarString;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.math3.util.IntegerSequence.range;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    private static double getFrequency(int i) {
        return CONCERT_A * Math.pow(2, (i - 24.0) / 12.0);
    }

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        HashMap<Character, GuitarString> charToString = new HashMap<>();
        for (int i : range(0, keyboard.length() - 1, 1)) {
            Character c = keyboard.charAt(i);
            GuitarString gs = new GuitarString(getFrequency(i));
            charToString.put(c, gs);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (charToString.containsKey(key)) {
                    GuitarString gs = charToString.get(key);
                    gs.pluck();
                }
            }

        /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString gs : charToString.values()) {
                sample += gs.sample();
            }

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            for (GuitarString gs : charToString.values()) {
                gs.tic();
            }
        }
    }
}
