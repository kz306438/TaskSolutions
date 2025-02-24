package org.stablesort;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Test 1: Integer array with duplicate values
        Integer[] numbers = {5, 3, 3, 4, 5, 1};
        System.out.println("Original array: " + Arrays.toString(numbers));
        StableSortWrapper.stableSort(numbers, Comparator.naturalOrder());
        System.out.println("Stable sorted array: " + Arrays.toString(numbers));

        // Test 2: Array of strings with custom comparator (sort by length)
        String[] words = {"banana", "apple", "kiwi", "pear", "mango", "plum"};
        System.out.println("Original array: " + Arrays.toString(words));
        StableSortWrapper.stableSort(words, Comparator.comparingInt(String::length));
        System.out.println("Stable sorted by length: " + Arrays.toString(words));

        // Test 3: Edge case - empty array
        String[] emptyArray = {};
        System.out.println("Original array: " + Arrays.toString(emptyArray));
        StableSortWrapper.stableSort(emptyArray, Comparator.naturalOrder());
        System.out.println("Sorted empty array: " + Arrays.toString(emptyArray));

        // Test 4: Array of custom objects (Person class) with duplicate names
        Person[] people = {
                new Person("Alice", "Smith"),
                new Person("Bob", "Brown"),
                new Person("Alice", "Johnson"),
                new Person("Charlie", "Davis")
        };
        System.out.println("Original array: " + Arrays.toString(people));
        StableSortWrapper.stableSort(people, Comparator.comparing(Person::getFirstName));
        System.out.println("Stable sorted by first name: " + Arrays.toString(people));
    }
}

// Helper class for custom object testing
class Person {
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
