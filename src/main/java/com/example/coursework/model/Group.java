package com.example.coursework.model;

import com.example.coursework.util.AlertUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * * Функція додавання/вилучення студента.
 * * Функція редагування старости.
 * * Функція зміни куратора.
 */

public class Group implements Comparable<Group> {
    private String name;
    private List<Student> students = new ArrayList<>();
    private Integer numberOfStudents;
    private Student headman;
    private Curator curator;
    private List<String> courses = new ArrayList<>();

    public Group() {

    }

    public Group(String name, List<Student> students, Integer numberOfStudents, Student headman, Curator curator, List<String> courses) {
        this.name = name;
        this.students = students;
        this.numberOfStudents = numberOfStudents;
        this.headman = headman;
        this.curator = curator;
        this.courses = courses;
    }

    public Group(Group other) {
        this.name = other.name;
        this.students = new ArrayList<>();

        for (Student student : other.students) {
            this.students.add(new Student(student));
        }
        this.numberOfStudents = other.numberOfStudents;
        this.headman = new Student(other.headman);
        this.curator = new Curator(other.curator);
        this.courses = new ArrayList<>(other.courses);
    }

    public Group(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addStudent(String student) {
        Student newStudent = new Student(student);
        addStudent(newStudent);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void removeStudent(String student) {
        Student studentToRemove = new Student(student);
        removeStudent(studentToRemove);
    }

    private boolean isInGroup(Student student) {
        for (Student st : this.students) {
            if (st.equals(student)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Group other) {
        return Integer.compare(this.retrieveStudentCount(), other.retrieveStudentCount());
    }

    public int retrieveStudentCount() {
        return students.size();
    }

    public List<String> retrieveStudentsNames() {
        List<String> students = new ArrayList<>();
        for (Student student : this.students) {
            students.add(student.getFullName());
        }
        return students;
    }

    public String retrieveCuratorName() {
        return curator.getFullName();
    }

    public String retrieveHeadmanName() {
        return headman.getFullName();
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Integer getNumberOfStudents() {
        return students.size();
    }

    public Student getHeadman() {
        return headman;
    }

    public Curator getCurator() {
        return curator;
    }

    public List<String> getCourses() {
        return this.courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeadman(Student newHeadman) {
        if (isInGroup(newHeadman)) {
            this.headman = newHeadman;
        } else {
            AlertUtils.showErrorAlert("Error Occurred", " ", "There is no student in group to be starosta");
        }
    }

    public void settleHeadmanString(String newHeadmanName) {
        Student newHeadman = new Student(newHeadmanName);
        setHeadman(newHeadman);
    }

    public void setCurator(Curator newCurator) {
        if (newCurator.isInList()) {
            if (this.curator != null) {
                this.curator.resetGroup();
            }
            newCurator.setGroup(this);
            this.curator = newCurator;
        } else {
            AlertUtils.showErrorAlert("Error Occurred", " ", "This one is already curator in group ");
        }
    }

    public void settleCuratorString(String newCuratorName) {
        Curator newCurator = new Curator(newCuratorName);
        setCurator(newCurator);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setNumberOfStudents() {
        this.numberOfStudents = students.size();
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return name;
    }
}
