package com.example.coursework.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// todo: implement method which return group in which teacher is curator
public class Teacher {
    // declare all teachers that can be curators
    // each teacher can be curator, if he/she is in list and is not curator now
    private final String fullName;
    private Group group;
    private static final List<String> possibleCurators = new ArrayList<>(
            Arrays.asList(
                    "Shevchenko Taras", "Petrenko Dmytro", "Kravchuk Andriy", "Hrytsenko Oleksiy", "Savchenko Nadiya",
                    "Bondarenko Olena", "Tkachenko Iryna", "Kovalenko Yuriy", "Vasylenko Kateryna", "Zelenska Oksana",
                    "Ponomarenko Larysa", "Kryvko Roman", "Danylenko Bohdan", "Marchenko Ivan", "Fedorenko Mykola",
                    "Zakharov Volodymyr", "Tymoshenko Yulia", "Stepanenko Ihor", "Sydorenko Artem", "Kovalchuk Lilia",
                    "Mykhailenko Olga", "Vorobey Anatoliy", "Berezhna Iryna", "Havrylyuk Mykhailo", "Kuznetsova Maria",
                    "Soroka Pavlo", "Boyko Yaroslav", "Melnyk Tetyana", "Shevchuk Vasyl", "Bilous Denys",
                    "Korolenko Petro", "Tkachuk Ruslan", "Pavlenko Sergiy", "Kolesnikova Anna", "Berezin Mykola",
                    "Yurchenko Viktor", "Semenyuk Vitaliy", "Kostyuk Natalia", "Hnatyuk Valeriy", "Voronenko Yuriy"
            ));


    public Teacher(String fullName) {
        this.fullName = fullName;
    }

    public boolean isCurator() {
        return (group != null);
    }

    public boolean isPossibleToBeCurator() {
        return (isInList() && (group != null));
    }

    private boolean isInList() {
        for (String key : possibleCurators) {
            if (key.equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public void resetGroup() {
        this.group = null;
    }
}
