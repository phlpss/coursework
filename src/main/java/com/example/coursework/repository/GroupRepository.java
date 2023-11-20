package com.example.coursework.repository;

import com.example.coursework.util.AlertUtils;
import com.example.coursework.model.Group;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class GroupRepository {
    private final List<Group> groups = initializeGroups();
    private final String jsonFilePath = "C:\\Users\\KATERYNKA\\Desktop\\LPNU\\term 3\\CouseWork\\coursework\\groups.json";


    private List<Group> initializeGroups() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(jsonFilePath), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtils.showErrorAlert("Error reading file", "Error reading file", "Error reading file");
        }
        return null;
    }

    public void flushToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(jsonFilePath), groups);
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtils.showErrorAlert("Error writing to file", e.getMessage(), "Error writing to file");
        }
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<String> getGroupNames() {
        List<String> groupNames = new ArrayList<>();
        assert groups != null;
        for (Group group : groups) {
            groupNames.add(group.getName());
        }
        return groupNames;
    }

    public List<String> getStudents(Group group) {
        List<String> students = new ArrayList<>();
        students.addAll(group.retrieveStudentsNames());
        return students;
    }

    public List<String> getCourses() {
        assert groups != null;
        return groups.stream().flatMap(group -> group.getCourses().stream()).distinct().toList();
    }

    public Optional<Group> getGroupByName(String name) {
        assert groups != null;
        return groups.stream()
                .filter(el -> el.getName().equals(name))
                .findFirst();
    }

    public List<Group> getGroupsByCourseName(String courseName) {
        assert groups != null;
        return groups.stream()
                .filter(el -> el.getCourses().contains(courseName))
                .toList();
    }

    public List<Group> getGroupsByCourseYear(String selectedCourseYear) {
        assert groups != null;
        return groups.stream()
                .filter(group -> {
                    String groupName = group.getName();
                    String yearPart = groupName.substring(groupName.lastIndexOf('-') + 1);
                    try {
                        int year = Character.getNumericValue(yearPart.charAt(0));
                        return year == Integer.parseInt(selectedCourseYear);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
