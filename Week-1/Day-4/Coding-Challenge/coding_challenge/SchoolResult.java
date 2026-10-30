package com.training.coding_challenge;

class StudentResult {

    private String studentName;
    private int[] marks;

    public StudentResult(String studentName, int[] marks) {
        this.studentName = studentName;
        this.marks = marks;
    }

    public int calculateTotal() {
        int total = 0;

        for (int mark : marks) {
            total += mark;
        }

        return total;
    }

    public double calculateAverage() {
        return (double) calculateTotal() / marks.length;
    }

    public void displayResult() {
        double average = calculateAverage();

        System.out.println("Student: " + studentName);
        System.out.println("Total Marks: " + calculateTotal());
        System.out.println("Average: " + average);

        if (average >= 35) {
            System.out.println("Result: Pass");
        } else {
            System.out.println("Result: Fail");
        }
    }
}
public class SchoolResult {
	public static void main(String[] args) {
		int[] marks = {80, 75, 90, 85, 70};
        StudentResult result = new StudentResult("Arun", marks);
        result.displayResult();
	}

}
