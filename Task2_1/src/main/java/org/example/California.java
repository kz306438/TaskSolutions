package org.example;

import java.util.logging.Logger;

public class    California {
    private static final Logger logger = Logger.getLogger(California.class.getName());

    public static void main(String[] args) {
        // Создаем массив кандидатов
        Candidate[] candidates = {
                new Candidate("John", "Doe"),
                new Candidate("Alice", "Smith"),
                new Candidate("Bob", "Johnson"),
                new Candidate("Carol", "Davis"),
                new Candidate("Dave", "Brown")
        };

        // Логируем исходный массив
        logger.info("Before sorting:");
        for (Candidate candidate : candidates) {
            logger.info(candidate.toString());
        }

        // Создаем экземпляр QuickSort и сортируем массив
        QuickSort quickSort = new QuickSort();
        quickSort.sort(candidates);

        // Логируем отсортированный массив
        logger.info("\nAfter sorting:");
        for (Candidate candidate : candidates) {
            logger.info(candidate.toString());
        }
    }
}
