package com.example.coursework.other;

import java.util.List;

/**
 * * Функція додавання/вилучення студента.
 * * Функція редагування старости.
 * * Функція зміни куратора.
 */

public class Group implements Comparable<Group> {
    private String name;
    private Integer courseYear;
    private List<Student> students;
    private Student headman;
    private Teacher curator;


    public String getName() {
        return name;
    }

    public Integer getCourseYear() {
        return courseYear;
    }

    public void changeCurator(Teacher newCurator) {
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

    public void changeHeadman(Student newStarosta) {
        if (isInGroup(newStarosta)) {
            this.headman = newStarosta;
        } else {
            AlertUtils.showErrorAlert("Error Occurred", " ", "There is no student in group to be starosta");
        }
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
}
