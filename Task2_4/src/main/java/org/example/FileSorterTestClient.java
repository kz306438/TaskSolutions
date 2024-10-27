package org.example;

import java.io.File;
import java.util.Scanner;

public class FileSorterTestClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for directory path
        System.out.print("Please enter the directory path for testing: ");
        String testDirectoryPath = scanner.nextLine();
        File dir = new File(testDirectoryPath);
        if (!dir.isDirectory()) {
            System.out.println("Provided path is not a directory.");
            return;
        }

        // Prompt for sorting option
        System.out.print("Choose sorting option (-s for size, -t for time, -n for name): ");
        String sortOption = scanner.nextLine();

        // Prompt for sorting order
        System.out.print("Choose sorting order (asc for ascending, desc for descending): ");
        String sortOrder = scanner.nextLine();

        // Call method to perform sorting test
        testSorting(dir, sortOption, sortOrder);
    }

    /**
     * Method to test sorting based on input parameters
     * @param dir Directory containing files to sort
     * @param sortOption Sorting option, e.g., "-s" for size, "-t" for time, "-n" for name
     * @param sortOrder Sorting order, "asc" for ascending or "desc" for descending
     */
    private static void testSorting(File dir, String sortOption, String sortOrder) {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("Directory is empty or cannot be read.");
            return;
        }

        System.out.printf("%nTesting sorting by %s in %s order:%n",
                sortOption.equals("-s") ? "size" : sortOption.equals("-t") ? "time" : "name",
                sortOrder);

        // Sort files using FileSorter
        try {
            File[] filesCopy = files.clone();  // Clone array for each test
            FileSorter.stableSort(filesCopy, FileSorter.getComparator(sortOption, sortOrder));

            // Print sorted files
            for (File file : filesCopy) {
                System.out.printf("Name: %s, Size: %d bytes, Last Modified: %tc%n",
                        file.getName(), file.length(), file.lastModified());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid sorting option: " + e.getMessage());
        }
    }
}
