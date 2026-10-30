
/*
 * Concept: Composition and Encapsulation
 */
package com.training.coding_challenge;

import java.util.ArrayList;
import java.util.List;

class Course {

    private String courseName;
    private List<String> students = new ArrayList<>();

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void enrollStudent(String studentName) {
        if (!students.contains(studentName)) {
            students.add(studentName);
            System.out.println(studentName + " enrolled successfully");
        } else {
            System.out.println("Student already enrolled");
        }
    }

    public void displayCourseDetails() {
        System.out.println("Course: " + courseName);
        System.out.println("Enrolled Students:");

        for (String student : students) {
            System.out.println(student);
        }
    }
}

public class OnlineCourse {
	public static void main(String[] args) {
		   Course course = new Course("Java Programming");
	        course.enrollStudent("Anil");
	        course.enrollStudent("Rahul");
	        course.displayCourseDetails();
	}
}
