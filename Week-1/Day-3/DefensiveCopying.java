/*
 * What is defensive copying?(Copy mutable data to protect internal state.)
1.Defensive copying means creating a copy of a mutable object. 
2.so that external code cannot modify the original object's internal data.
 */

package com.training.objectmethods;

import java.util.ArrayList;
import java.util.List;

class Student1 {
    private final List<String> skills;

    public Student1(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }
}

public class DefensiveCopying {
	public static void main(String[] args) {
        List<String> skills = new ArrayList<>();
        skills.add("Java");

        Student1 s1 = new Student1(skills);

        skills.add("Python"); // Changes Student's internal list

        System.out.println(s1.getSkills());

        s1.getSkills().add("SQL"); // Also changes internal list

        System.out.println(s1.getSkills());
    }
}
