package com.example.coursework.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupManager {
    /**
     * Пошук групи по назві.
     * Вивести список груп по курсу.
     * Відсортувати групи методом Quick sort по кількості студентів.
     */
    List<Group> groups;
    List<String> courseNames;
    String errorMessage;

    public GroupManager(List<Group> groups) {
        this.groups = groups;

    }
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Group getGroup(String groupName) {
        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                return group;
            }
        }
        AlertUtils.showErrorAlert("Error Occurred", " ", "There is no group with this name");
        return null;
    }

    public List<Group> getGroups(Integer courseYear) {
        List<Group> result = new ArrayList<Group>();

        for (Group group : groups) {
            if (Objects.equals(group.getCourseYear(), courseYear)) {
                result.add(group);
            }
        }

        return result;
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
}
