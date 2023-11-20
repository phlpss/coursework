package com.example.coursework.model;

import java.util.Objects;

public class Student {
    private String fullName;

    public Student (){}

    public Student(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(fullName, student.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}