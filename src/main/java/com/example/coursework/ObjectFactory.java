package com.example.coursework;

import com.example.coursework.repository.GroupRepository;

public class ObjectFactory {
    private static GroupRepository groupRepository;
    public static GroupRepository groupRepository(){
        return groupRepository != null? groupRepository: new GroupRepository();
    }

    //other repositories
}
