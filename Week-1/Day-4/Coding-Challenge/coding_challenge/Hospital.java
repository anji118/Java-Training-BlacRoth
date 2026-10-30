/*
 * Concept: Encapsulation and Object Management
 */
package com.training.coding_challenge;

class Patient {

    private int patientId;
    private String patientName;
    private String disease;

    public Patient(int patientId, String patientName, String disease) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.disease = disease;
    }

    public void updateDisease(String disease) {
        this.disease = disease;
    }

    public void displayPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("Patient Disease: " + disease);
    }
}

public class Hospital {

    public static void main(String[] args) {

        Patient pt = new Patient(11143, "Balaji", "AIDS");

        // Display original disease
        System.out.println("Before Updating:");
        pt.displayPatientDetails();

        // Update disease/status
        pt.updateDisease("Negative");

        // Display updated status
        System.out.println("\nAfter Updating:");
        pt.displayPatientDetails();
    }
}
