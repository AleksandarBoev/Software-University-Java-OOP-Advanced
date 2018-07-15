package demo.comparatorAndComparable;

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
}
