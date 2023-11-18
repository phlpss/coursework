package com.example.coursework.other;

import java.util.ArrayList;
import java.util.List;

/**
 * * Функція додавання/вилучення студента.
 * * Функція редагування старости.
 * * Функція зміни куратора.
 */

public class Group implements Comparable<Group> {
    private String name;
    private Student headman;
    private Curator curator;
    private List<Student> students = new ArrayList<>();
    private List<String> courses = new ArrayList<>();

    public Group (String name) {
        this.name = name;
    }

    //TODO finish method
    public Group(Group other) {
        this.name = other.name;
    }

    public Group() {

    }

    public String getName() {
        return name;
    }
    public List<String> getCourseNames() {
        return courses;
    }


    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    private boolean isInGroup(Student student) {
        for (Student st : this.students) {
            if (st.equals(student)) {
                return true;
            }
        }
        return false;
    }

    public int getStudentCount() {
        return students.size();
    }

    @Override
    public int compareTo(Group other) {
        return Integer.compare(this.getStudentCount(), other.getStudentCount());
    }

    //TODO: get course year from name of group
    public Integer getCourseYear() {
        return null;
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

    public void setCurator(Curator newCurator) {
        if (newCurator.isPossibleToBeCurator()) {
            if (this.curator != null) {
                this.curator.resetGroup();
            }
            this.curator = newCurator;
            newCurator.setGroup(this);
        } else {
            AlertUtils.showErrorAlert("Error Occurred", " ", "This one is already curator in group ");
        }
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
    public List<String> getCourses() {
        return this.courses;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", headman=" + headman +
                ", curator=" + curator +
                ", students=" + students +
                ", courses=" + courses +
                '}';
    }
}
