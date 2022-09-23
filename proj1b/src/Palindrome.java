import java.util.Objects;

public class Palindrome {
    /* Utility class should not be instantiated */
    private Palindrome() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static boolean isPalindrome(String word) {
        // Optional optimization
        if (word.length() <= 1) {
            return true;
        }

        // Check for character symmetry
        Deque<Character> d = ArrayDeque.fromString(word);
        while (d.size() >= 2) {
            char first = d.removeFirst();
            char last = d.removeLast();

            if (!Objects.equals(first, last)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        // Optional optimization
        if (word.length() <= 1) {
            return true;
        }

        // Check for character symmetry
        Deque<Character> d = ArrayDeque.fromString(word);
        while (d.size() >= 2) {
            char first = d.removeFirst();
            char last = d.removeLast();

            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }

    public static Deque<Character> wordToDeque(String word) {
        return ArrayDeque.fromString(word);
    }
}
