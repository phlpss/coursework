package com.example.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Curator {
    private String fullName;
    @JsonIgnore
    private Group group;
    private static final List<Curator> allCurators = new ArrayList<>();
    private static final List<String> possibleCurators = List.of(
            "Павло Курапов", "Ольга Терендій", "Оксана Грицай", "Тетяна Коротєєва", "Євгенія Левус",
            "Роман Кутельмах", "Оксана Шванова", "Сергій Похотько", "Іван Франко", "Тарас Шевченко"
    );

    public Curator() {
    }

    public Curator(Curator other) {
        this.fullName = other.fullName;
        this.group = new Group(other.group);
    }

    @JsonCreator
    public Curator(@JsonProperty("fullName") String fullName) {
        this.fullName = fullName;
        allCurators.add(this);
    }

    boolean isInList() {
        for (String key : possibleCurators) {
            if (key.equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    public void resetGroup() {
        this.group = null;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Group getGroup() {
        return group;
    }
    public String getFullName() {
        return fullName;
    }

    public static List<String> getAvailableCurators() {
        // Get names of curators who are currently assigned to a group
        List<String> assignedCurators = allCurators.stream()
                .filter(curator -> curator.getGroup() != null)
                .map(Curator::getFullName)
                .collect(Collectors.toList());

        // Return names from possibleCurators that are not in the assignedCurators list
        return possibleCurators.stream()
                .filter(possibleCurator -> !assignedCurators.contains(possibleCurator))
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return fullName;
    }

}
