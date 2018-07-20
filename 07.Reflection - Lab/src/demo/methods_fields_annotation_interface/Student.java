package demo.methods_fields_annotation_interface;

public class Student extends StudentBase implements Passes {
    private String name;
    private int age;
    public double grade;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.grade = 0.0;
    }

    protected Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return this.grade;
    }

    protected void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }

    @Override
    public void passes() {
        System.out.println("w/e");
    }
}
