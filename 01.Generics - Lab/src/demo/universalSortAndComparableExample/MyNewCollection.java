package demo.universalSortAndComparableExample;

public class MyNewCollection extends MyCollection<String> implements Comparable<String> {
    @Override
    public int compareTo(String o) {
        return 0;
    }
}
