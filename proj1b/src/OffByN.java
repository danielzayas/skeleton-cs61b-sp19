import java.util.Objects;

public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int a = x;
        int b = y;

        return Objects.equals(Math.abs(x - y), this.n);
    }
}
