package oop.finalexam.t2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class LearningCourse {
    private final String title;
    private String prerequisites;
    private String majorTopics;

    public LearningCourse(String title, String prerequisites, String majorTopics) {
        this.title = Objects.requireNonNull(title, "Course title cannot be null");
        this.prerequisites = Objects.requireNonNull(prerequisites, "Prerequisites cannot be null");
        this.majorTopics = Objects.requireNonNull(majorTopics, "Major topics cannot be null");
    }

    public String getTitle() {
        return title;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public String getMajorTopics() {
        return majorTopics;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = Objects.requireNonNull(prerequisites, "Prerequisites cannot be null");
    }

    public void setMajorTopics(String majorTopics) {
        this.majorTopics = Objects.requireNonNull(majorTopics, "Major topics cannot be null");
    }

    @Override
    public String toString() {
        return String.format("- Title: %s%n  Prerequisites: %s%n  Major Topics: %s%n",
                title, prerequisites, majorTopics);
    }
}

class Student {
    private final String name;
    private final List<LearningCourse> courses;

    public Student(String name) {
        this.name = Objects.requireNonNull(name, "Student name cannot be null");
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<LearningCourse> getCourses() {
        return Collections.unmodifiableList(courses); // Return unmodifiable view
    }

    public void addCourse(LearningCourse course) {
        courses.add(Objects.requireNonNull(course, "Course cannot be null"));
    }
}

public class UMS {
    private final List<Student> students;

    public UMS() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(Objects.requireNonNull(student, "Student cannot be null"));
    }

    public void printStudentData(Student student) {
        Objects.requireNonNull(student, "Student cannot be null");

        System.out.println("Student Name: " + student.getName());
        System.out.println("Enrolled Courses:");

        if (student.getCourses().isEmpty()) {
            System.out.println("  No courses enrolled");
        } else {
            student.getCourses().forEach(course ->
                    System.out.printf("  %s%n", course.toString().replaceAll("(?m)^", "  ")));
        }
    }

    public List<LearningCourse> getMyCourses(String studentName) {
        Objects.requireNonNull(studentName, "Student name cannot be null");

        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(studentName))
                .findFirst()
                .map(Student::getCourses)
                .orElse(Collections.emptyList());
    }

    public static void main(String[] args) {
        UMS ums = new UMS();

        Student aleksandre = new Student("Aleksandre Japharidze");

        aleksandre.addCourse(new LearningCourse(
                "Object-Oriented Programming.",
                "CS50 Introduction to programming.",
                "Java syntax and data structures; Procedural programming; Classes; Encapsulation, polymorphism, inheritance; Packages; Working with the network; Work with files; Working with text data; Work with the terminal."
        ));

        aleksandre.addCourse(new LearningCourse(
                "Computer Organization.",
                "CS50 Introduction to Programming.",
                "Representing and manipulating information; Machine-level representations of programs; Optimizing program performance; The memory hierarchy."
        ));

        aleksandre.addCourse(new LearningCourse(
                "Calculus II.",
                "MATH 150 Calculus I.",
                "Antiderivatives; Definite integrals; Techniques and applications of integration; Improper integrals; Infinite series."
        ));

        aleksandre.addCourse(new LearningCourse(
                "Mathematical Foundation of Computing.",
                "MATH 150 Calculus I; CS50 Introduction to Programming.",
                "Mathematical Logic; Elements of Discrete Mathematics; Elements of Sets Theory; Elements of Graph Theory; Elements of Combinatorics; Elements of Digital Systems."
        ));

        // This is a random example student with random subjects and random everything. This is purely for experimental purposes.
        Student james = new Student("James Bond");

        james.addCourse(new LearningCourse("Physics", "No Prerequisites", "IDK To be honest..."));
        james.addCourse(new LearningCourse("Organic Chemistry", "No Prerequisites", "Cooking(don't take it seriously)"));
        james.addCourse(new LearningCourse("Calculus III", "MATH 151 Calculus II", "IDK Yet..."));

        ums.addStudent(aleksandre);
        ums.addStudent(james);
        ums.printStudentData(aleksandre);
        ums.printStudentData(james);

        System.out.println("\nYour Courses:");
        List<LearningCourse> myCourses = ums.getMyCourses("Aleksandre Japharidze");
        myCourses.forEach(System.out::println);
    }
}