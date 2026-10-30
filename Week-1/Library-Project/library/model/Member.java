package com.training.library.model;



	    /**
	     * Creates a new library member.
	     */
	    public class Member {

	        private final int id;
	        private String name;
	        private boolean active;

	        public Member(int id, String name) {
	            if (id <= 0) {
	                throw new IllegalArgumentException(
	                        "Member ID must be positive");
	            }

	            validateName(name);

	            this.id = id;
	            this.name = name.trim();
	            this.active = true;
	        }

	        private void validateName(String name) {
	            if (name == null || name.isBlank()) {
	                throw new IllegalArgumentException(
	                        "Member name cannot be empty");
	            }
	        }

	        public int getId() {
	            return id;
	        }

	        public String getName() {
	            return name;
	        }

	        public boolean isActive() {
	            return active;
	        }

	        public void updateName(String name) {
	            validateName(name);
	            this.name = name.trim();
	        }

	        public void setActive(boolean active) {
	            this.active = active;
	        }

	        @Override
	        public String toString() {
	            return "Member ID: " + id
	                    + ", Name: " + name
	                    + ", Active: " + active;
	        }
	    }