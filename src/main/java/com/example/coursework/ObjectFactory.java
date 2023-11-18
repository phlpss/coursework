package com.example.coursework;

//import com.example.coursework.other.GroupService;
import com.example.coursework.repository.GroupRepository;

public class ObjectFactory {
    public static GroupRepository groupRepository;
    public static GroupRepository groupRepository(){
        return groupRepository != null? groupRepository: new GroupRepository();
    }
}
