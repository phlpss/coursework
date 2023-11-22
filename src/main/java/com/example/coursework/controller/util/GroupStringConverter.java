package com.example.coursework.controller.util;

import javafx.util.StringConverter;
import com.example.coursework.model.Group;

public class GroupStringConverter extends StringConverter<Group> {
    @Override
    public String toString(Group group) {
        // Return a string representation of the Group
        return group != null ? group.getName() : null;
    }

    @Override
    public Group fromString(String string) {
        // This method is not needed for a non-editable ListView
        // Return null or implement conversion logic if necessary
        return null;
    }
}
