package demo.comparatorAndComparable;

import java.util.Comparator;

public class Student implements Comparable<Student> {
    private double averageGrade;
    private String name;

    public Student(double averageGrade, String name) {
        this.averageGrade = averageGrade;
        this.name = name;
    }

    public double getAverageGrade() {
        return this.averageGrade;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Student anotherStudent) {
        int gradeComparisonResult = 0;
        gradeComparisonResult = Double.compare(this.averageGrade, anotherStudent.getAverageGrade());

        if (gradeComparisonResult != 0) {
            return gradeComparisonResult;
        } else {
            return this.name.compareTo(anotherStudent.getName());
        }
    }

    public static final class NameComparator implements Comparator<Student> {

        @Override
        public int compare(Student student1, Student student2) {
            return student1.name.compareTo(student2.name);
        }
    }

    public static final class AverageGradeComparator implements Comparator<Student> {

        @Override
        public int compare(Student student1, Student student2) {
            return Double.compare(student1.averageGrade, student2.averageGrade);
        }
    }

    public static final class CustomComparator implements Comparator<Student> {

        @Override
        public int compare(Student student1, Student student2) {
            int comparisonResult = Double.compare(student1.averageGrade, student2.averageGrade);
            if (comparisonResult != 0)
                return comparisonResult;
            else
                return student1.name.compareTo(student2.name);
        }
    }
}
