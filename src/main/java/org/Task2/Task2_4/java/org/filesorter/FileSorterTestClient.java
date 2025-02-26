package org.Task2.Task2_4.java.org.filesorter;

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

        // Prompt for combined sorting options
        System.out.print("Choose sorting options (e.g., 'stn' for size, then time, then name): ");
        String sortOptions = scanner.nextLine();

        // Call method to perform sorting test
        testSorting(dir, sortOptions);
    }

    /**
     * Method to test sorting based on input parameters
     * @param dir Directory containing files to sort
     * @param sortOptions Sorting options, e.g., "stn" for size, then time, then name
     */
    private static void testSorting(File dir, String sortOptions) {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("Directory is empty or cannot be read.");
            return;
        }

        System.out.printf("%nTesting sorting by options: %s%n", sortOptions);

        // Sort files using FileSorter
        try {
            File[] filesCopy = files.clone();  // Clone array for each test
            FileSorter.stableSort(filesCopy, FileSorter.getCombinedComparator(sortOptions));

            // Print sorted files
            for (File file : filesCopy) {
                System.out.printf("Name: %s, Size: %d bytes, Last Modified: %tc%n",
                        file.getName(), file.length(), file.lastModified());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid sorting options: " + e.getMessage());
        }
    }
}
