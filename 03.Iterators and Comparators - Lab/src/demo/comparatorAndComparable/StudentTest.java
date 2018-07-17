package demo.comparatorAndComparable;

import javafx.collections.transformation.SortedList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class StudentTest {
    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>(Arrays.asList(
                new Student(3.5, "Pesho"),
                new Student(4.5, "Gosho"),
                new Student(2.5, "Ana"),
                new Student(6.0, "Sasho"),
                new Student(2.5, "Simona")));

        List<Student> studentList1 = new ArrayList<>(studentList);

        studentList = studentList.stream() //first way of sorting, using MY built in .compareTo method
                .sorted((s1, s2) -> {
                    return s1.compareTo(s2);
                }).collect(Collectors.toList());

        StudentComparator studentComparator = new StudentComparator();
        studentList1 = studentList.stream() //second way of sorting:
                .sorted((s1, s2) -> {
                    return studentComparator.compare(s1, s2);
                }).collect(Collectors.toList());

//        studentList1.sort(new StudentComparator()); //third way

        Set<Student> studentSet = new TreeSet<>(new StudentComparator()); // fourth way of sorting. The set always stays sorted by my criteria.
        //IMPORTANT: even without adding "new StudentComparator()" the set would be sorted correctly, because then it would use my .compareTo method.
        //But having multiple Comparators means I can sort the set in multiple ways
        studentSet.add(new Student(3.5, "Pesho"));
        studentSet.add(new Student(4.5, "Gosho"));
        studentSet.add(new Student(6.0, "Sasho"));
        studentSet.add(new Student(2.5, "Ana"));
        studentSet.add(new Student(2.5, "Simona"));

        Set<Student> studentSet2 = new TreeSet<>(new Student.NameComparator()); // fifth way of sorting by name or by whatever I put in there.
        studentSet2.add(new Student(3.5, "Pesho"));
        studentSet2.add(new Student(4.5, "Gosho"));
        studentSet2.add(new Student(6.0, "Sasho"));
        studentSet2.add(new Student(2.5, "Ana"));
        studentSet2.add(new Student(2.5, "Simona"));
        //TODO what's better? Write the comparators in the class or outside the class?
        //main ends here
    }
}
