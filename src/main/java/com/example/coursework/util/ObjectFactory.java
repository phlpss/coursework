package com.example.coursework.util;

import com.example.coursework.repository.GroupRepository;

public class ObjectFactory {
    public static GroupRepository groupRepository;

    public static GroupRepository groupRepository() {
        return groupRepository != null ? groupRepository : new GroupRepository();
    }
}
