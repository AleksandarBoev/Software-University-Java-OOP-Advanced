package demo.universalSortAndComparableExample;

public class Student implements Comparable<Student> {
    private String name;
    private double averageGradeSemester1;
    private double averageGradeSemester2;

    public Student(double averageGradeSemester1, double averageGradeSemester2, String name) {
        this.name = name;
        this.averageGradeSemester1 = averageGradeSemester1;
        this.averageGradeSemester2 = averageGradeSemester2;
    }

    public double getAverageGradeSemester1() {
        return this.averageGradeSemester1;
    }

    public double getAverageGradeSemester2() {
        return this.averageGradeSemester2;
    }

    @Override
    public int compareTo(Student student) {
        double currentResult = this.getTotalAverageGrade();
        double otherResult = student.getTotalAverageGrade();

        if (currentResult > otherResult) {
            return 1;
        } else if (currentResult == otherResult) {
            return 0;
        } else {
            return -1;
        }
    }

    private double getTotalAverageGrade() {
        return (this.averageGradeSemester1 + this.averageGradeSemester2) / 2.0;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Total Average Grade: %.2f", this.name, this.getTotalAverageGrade());
    }
}
