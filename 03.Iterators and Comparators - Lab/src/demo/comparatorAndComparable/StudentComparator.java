package demo.comparatorAndComparable;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student student1, Student student2) {
        int gradeComparisonResult = 0;
        gradeComparisonResult = Double.compare(student1.getAverageGrade(), student2.getAverageGrade());

        if (gradeComparisonResult != 0) {
            return gradeComparisonResult;
        } else {
            return student1.getName().compareTo(student2.getName());
        }
    }
}
