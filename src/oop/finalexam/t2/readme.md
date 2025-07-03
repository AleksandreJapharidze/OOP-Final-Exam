# 🎓 University Management System (UMS) - Java Console Program

This is a simple University Management System (UMS) built using Java, showcasing how students and their enrolled academic courses can be modeled and managed in an object-oriented way. The system allows storing students, their courses, and querying the enrolled course data by student name.

---

## 📌 Features

- Define university courses with:
    - Title
    - Prerequisites
    - Major topics covered
- Create student profiles
- Assign courses to students
- Display detailed course information per student
- Fetch and list courses by student name

---

## 🧠 Object-Oriented Design

The program uses clean and minimal object-oriented principles, encapsulating functionality in three main classes:

### `LearningCourse`

Represents a university course.

- **Fields**:
    - `title`: Course title (immutable)
    - `prerequisites`: What knowledge is expected beforehand
    - `majorTopics`: Core content covered in the course
- **Key Methods**:
    - Getters and setters for course info
    - Overridden `toString()` method for formatted output

---

### `Student`

Represents a student enrolled in the university.

- **Fields**:
    - `name`: Full name of the student (immutable)
    - `courses`: List of `LearningCourse` instances
- **Key Methods**:
    - `addCourse(LearningCourse)`: Adds a new course to the student's list
    - `getCourses()`: Returns an unmodifiable view of the enrolled courses

---

### `UMS` (University Management System)

Core controller class managing all student data and operations.

- **Fields**:
    - `students`: List of all registered students
- **Key Methods**:
    - `addStudent(Student)`: Adds a new student to the system
    - `printStudentData(Student)`: Nicely prints student name and their enrolled courses
    - `getMyCourses(String studentName)`: Returns the list of courses for the student with the given name

---

## 🧪 Sample Data & Output

In the `main()` method, the following actions take place:

- Two students are created:
    - `Aleksandre Japharidze` with real course data
    - `James Bond` with humorous, placeholder course data for demonstration
- Courses are added to each student
- Students are added to the `UMS`
- Data is printed to the console
- The program fetches and lists Aleksandre’s courses again using `getMyCourses("Aleksandre Japharidze")`

UML Diagram:
---
![UML Diagram.png](..%2F..%2F..%2F..%2F..%2F..%2FUsers%2FStudent%2FDownloads%2FUML%20Diagram.png)![UML Diagram]()