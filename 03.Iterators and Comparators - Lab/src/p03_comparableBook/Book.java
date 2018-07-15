package p03_comparableBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Comparable<Book> {
    private String title;
    private int year;
    private List<String> authors;

    public Book(String title, int year, String... authors) {
        setTitle(title);
        setYear(year);
        setAuthors(authors);
    }

    public String getTitle() {
        return this.title;
    }
    
    private void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return this.year;
    }

    private void setYear(int year) {
        this.year = year;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    private void setAuthors(String... authors) {
        this.authors = new ArrayList<>(Arrays.asList(authors)); //this is done, because Arrays.asList returns a fixed size list
        //which can't be changed (will always have the same size). Doing things this way fixes this problem.
    }

    @Override
    public int compareTo(Book anotherBook) {
        int titleComparisonResult = this.title.compareTo(anotherBook.getTitle()); // TODO is it good practise just to use anotherBook.title?
        if (titleComparisonResult != 0) {
            return titleComparisonResult;
        } else {
            return Integer.compare(this.year, anotherBook.getYear());
        }
    }
}
