package org.example;

import java.util.ArrayList;
import java.util.List;

public class California {
    public static void main(String[] args) {
        // Создаем массив кандидатов
        Candidate[] candidates = {
                new Candidate("John", "Doe"),
                new Candidate("Alice", "Smith"),
                new Candidate("Bob", "Johnson"),
                new Candidate("Carol", "Davis"),
                new Candidate("Dave", "Brown")
        };

        // Выводим исходный массив
        System.out.println("Before sorting:");
        for (Candidate candidate : candidates) {
            System.out.println(candidate);
        }

        // Создаем экземпляр QuickSort и сортируем массив
        QuickSort quickSort = new QuickSort();
        quickSort.sort(candidates);

        // Выводим отсортированный массив
        System.out.println("\nAfter sorting:");
        for (Candidate candidate : candidates) {
            System.out.println(candidate);
        }
    }
}

