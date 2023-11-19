package com.example.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// todo: implement method which return group in which teacher is curator
public class Curator {
    // declare all teachers that can be curators
    // each teacher can be curator, if he/she is in list and is not curator now
    private String fullName;
    private Group group;
    private static final List<Curator> allCurators = new ArrayList<>();

    private static final List<String> possibleCurators = List.of(
            "Павло Курапов", "Ольга Терендій", "Оксана Грицай", "Тетяна Коротєєва", "Євгенія Левус",
            "Роман Кутельмах", "Оксана Шванова", "Сергій Похотько", "Іван Франко", "Тарас Шевченко"
    );

    public Curator() {
    }

    @JsonCreator
    public Curator(@JsonProperty("fullName") String fullName) {
        this.fullName = fullName;
        allCurators.add(this);
    }

    public boolean isCurator() {
        return (group != null);
    }

    boolean isInList() {
        for (String key : possibleCurators) {
            if (key.equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean existsCuratorWithName(String candidateName) {
        return allCurators.stream()
                .anyMatch(curator -> curator.getFullName().equals(candidateName));
    }
    boolean isPossibleToBeCurator() {
        return (isInList() && !isCurator());
    }
    boolean isPossibleToBeCurator(String candidate) {
        return possibleCurators.contains(candidate) && !existsCuratorWithName(candidate);
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
        // Get names of curators who are not assigned to any group
        List<String> assignedCurators = allCurators.stream()
                .filter(curator -> curator.getGroup() != null)
                .map(Curator::getFullName)
                .collect(Collectors.toList());

        // Filter possible curators who are not in the assignedCurators list
        return possibleCurators.stream()
                .filter(possibleCurator -> !assignedCurators.contains(possibleCurator))
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "Curator{" +
                "fullName='" + fullName + '\'' +
                '}';
    }

}
