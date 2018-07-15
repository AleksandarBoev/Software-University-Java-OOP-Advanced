package p04_bookComparator;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        int titleComparisonResult = book1.getTitle().compareTo(book2.getTitle());
        if (titleComparisonResult != 0) {
            return titleComparisonResult;
        } else {
            return Integer.compare(book1.getYear(), book2.getYear());
        }
    }
}
