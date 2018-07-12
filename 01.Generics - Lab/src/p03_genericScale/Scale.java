package p03_genericScale;

public class Scale<T extends Comparable<T>> {
    private T left;
    private T right;

    public Scale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getHeavier() {
        int comparisonResult = this.left.compareTo(this.right);
        if (comparisonResult > 0) {
            return this.left;
        } else if (comparisonResult < 0) {
            return this.right;
        } else {
            return null;
        }
    }
}
