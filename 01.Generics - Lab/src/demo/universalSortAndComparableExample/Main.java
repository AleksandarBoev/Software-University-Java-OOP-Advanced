package demo.universalSortAndComparableExample;

public class Main {
    public static void main(String[] args) {
        Sort<Student> studentSort = new Sort<>();
        Student[] students = new Student[]{
                new Student(2.5, 3.5, "Ivan"),
                new Student(4.5, 5.4, "Gosho"),
                new Student(2.0, 3.5, "Aleksandar")};

        students = studentSort.sortArrayAscending(students);
        for (Student student : students) {
            System.out.println(student);
        }

        MyCollection myCollection = new MyCollection();
        Sort stuff = new Sort();
//        students = stuff.sortArrayAscending(students); //compile time error!
    }

}
