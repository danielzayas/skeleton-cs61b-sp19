import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    /* static Palindrome palindrome = new Palindrome(); */

    @Test
    public void testIsPalindrome() {
        assertTrue(Palindrome.isPalindrome("racecar"));
        assertTrue(Palindrome.isPalindrome("noon"));
        assertTrue(Palindrome.isPalindrome("a"));
        assertTrue(Palindrome.isPalindrome(""));

        assertFalse(Palindrome.isPalindrome("horse"));
        assertFalse(Palindrome.isPalindrome("aaabaa"));
        assertFalse(Palindrome.isPalindrome("ab"));
    }

    @Test
    public void testWordToDeque() {
        Deque d = Palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
} //    Uncomment this class once you've created your Palindrome class.