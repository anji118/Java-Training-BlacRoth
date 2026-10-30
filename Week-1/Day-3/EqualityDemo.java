
/**
 * 1.equals() method: It is used to compare two objects based on their content or values rather than their memory references.
   2.hashCode() method: It returns an integer value (hash code) for an object, which is used by hash-based collections such as HashMap and HashSet.
   3.Consistency rule: If two objects are equal according to equals(), they must have the same hashCode().
 */

package com.training.objectmethods;

import java.util.Objects;

class Employee1 {
    private int id;
    private String name;

    public Employee1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Compare objects based on their values
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Employee1 employee = (Employee1) obj;

        return id == employee.id
                && Objects.equals(name, employee.name);
    }

    // Generate hash code using the same fields
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class EqualityDemo {
    public static void main(String[] args) {
        Employee1 e1 = new Employee1(101, "Ravi");
        Employee1 e2 = new Employee1(101, "Ravi");

        System.out.println(e1.equals(e2));
        System.out.println(e1.hashCode() == e2.hashCode());
    }

}
