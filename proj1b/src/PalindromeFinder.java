/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void findOffByNPalindromes(int n) {
        int minLength = 4;
        In in = new In("/Users/danielzayas/education/skeleton-cs61b-sp19/library-sp19/data/words.txt");

        CharacterComparator offByN = new OffByN(n);  // TODO: use OffByN

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && Palindrome.isPalindrome(word, offByN)) {
                System.out.println(word);
            }
        }
    }

    public static void findPalindromes() {
        int minLength = 4;
        In in = new In("/Users/danielzayas/education/skeleton-cs61b-sp19/library-sp19/data/words.txt");
        // Palindrome palindrome = new Palindrome();  // use static utility method

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && Palindrome.isPalindrome(word)) {
                System.out.println(word);
            }
        }
    }

    public static void main(String[] args) {
        findOffByNPalindromes(1);
    }
} // Uncomment this class once you've written isPalindrome.