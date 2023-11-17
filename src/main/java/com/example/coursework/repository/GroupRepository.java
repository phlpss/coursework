package com.example.coursework.repository;

import com.example.coursework.other.Group;

import java.util.List;

public class GroupRepository {

    private List<Group> groups;

    public List<Group> getGroups(){
        return groups;
    }

    public Group getGroupByName(String name){
        return null;
    }

    //other methods
}
