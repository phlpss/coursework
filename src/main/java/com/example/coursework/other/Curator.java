package com.example.coursework.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// todo: implement method which return group in which teacher is curator
public class Curator {
    // declare all teachers that can be curators
    // each teacher can be curator, if he/she is in list and is not curator now
    private String fullName;
    private Group group;
    private static final List<String> possibleCurators = List.of(
                    "Павло Курапов", "Ольга Терендій", "Оксана Грицай", "Тетяна Коротєєва", "Євгенія Левус", "Роман Кутельмах"
            );

    public Curator() {
    }

    public Curator(String fullName) {
        this.fullName = fullName;
    }

    public boolean isCurator() {
        return (group != null);
    }

    public boolean isPossibleToBeCurator() {
        return isInList();
    }

    private boolean isInList() {
        for (String key : possibleCurators) {
            if (key.equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void resetGroup() {
        this.group = null;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Curator{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
