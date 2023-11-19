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

    private List<Group> initializeGroups() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("C:\\Users\\KATERYNKA\\Desktop\\LPNU\\term 3\\CouseWork\\coursework\\src\\main\\resources\\json\\groups.json"), new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtils.showErrorAlert("Error reading file","Error reading file","Error reading file");
        }
        return null;
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
        students.addAll(group.getStudents());
        return students;
    }

    public List<String> getCourses() {
        return groups.stream().flatMap(group -> group.getCourses().stream()).distinct().toList();
    }

    public Optional<Group> getGroupByName(String name) {
        return groups.stream()
                .filter(el -> el.getName().equals(name))
                .findFirst();
    }

//    public List<Group> getGroupsByYear(Integer courseYear) {
//        return groups.stream()
//                .filter(el -> el.getCourseYear().equals(courseYear))
//                .toList();
//    }

    public List<Group> getGroupsByCourseName(String courseName) {
        return groups.stream()
                .filter(el -> el.getCourseNames().contains(courseName))
                .toList();
    }

    public List<Group> getGroupsByCourseYear(String selectedCourseYear) {
        return groups.stream()
                .filter(group -> {
                    String groupName = group.getName();String yearPart = groupName.substring(groupName.lastIndexOf('-') + 1);
                    try {
                        int year = Integer.parseInt(yearPart);
                        return year == Integer.parseInt(selectedCourseYear);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    public void save(Group group) {
        var existingGroup = groups.stream()
                .filter(el -> el.getName().equals(group.getName()))
                .findFirst();
        if (existingGroup.isPresent()) {
            Group existGroup = existingGroup.get();
            existGroup = group;
        } else groups.add(group);
        flushToFile();
    }

    public List<Group> sortByStudentsAmount() {
        quickSort(0, groups.size() - 1);
        return new ArrayList<>(groups);
    }

    public void quickSort(int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(begin, end);

            quickSort(begin, partitionIndex - 1);
            quickSort(partitionIndex + 1, end);
        }
    }

    private int partition(int begin, int end) {
        Group pivot = groups.get(end);
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (groups.get(j).compareTo(pivot) <= 0) {
                i++;

                Group swapTemp = groups.get(i);
                groups.set(i, groups.get(j));
                groups.set(j, swapTemp);
            }
        }

        Group swapTemp = groups.get(i + 1);
        groups.set(i + 1, groups.get(end));
        groups.set(end, swapTemp);

        return i + 1;
    }

    private void flushToFile() {

    }
}
