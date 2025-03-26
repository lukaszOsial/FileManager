package fileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String currentDir = ".";

		try {
			while (true) {
				System.out.println("\n==== File manager ====");
				System.out.println("1. View file list");
				System.out.println("2. Create new file");
				System.out.println("3. Delete the file");
				System.out.println("4. Read the file");
				System.out.println("5. Add text to file");
				System.out.println("6. Exit");
				System.out.println("Select option: ");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1 -> listFiles(currentDir);
				case 2 -> {
					System.out.println("Enter the file name: ");
					String newFileName = scanner.nextLine();
					createFile(currentDir, newFileName);
				}
				case 3 -> {
					System.out.println("Enter the file name to delete: ");
					String fileToDelete = scanner.nextLine();
					deleteFile(currentDir, fileToDelete);
				}
				case 4 -> {
					System.out.println("Enter the file name to read: ");
					String fileToRead = scanner.nextLine();
					readFile(currentDir, fileToRead);
				}
				case 5 -> {
					System.out.println("Enter the file name to read: ");
					String fileToRead = scanner.nextLine();
					System.out.println("Enter text to add the file: ");
					String textToAdd = scanner.nextLine();
					addTextToFile(currentDir, fileToRead, textToAdd);
				}
				case 6 -> {
					System.out.println("Exit from file manager");
					return;
				}
				default -> System.out.println("Bad option, please try again.");
				}
			}
		} finally {
			scanner.close();
		}
	}

	private static void listFiles(String directory) {
		File dir = new File(directory);
		File[] files = dir.listFiles();

		if (files != null && files.length > 0) {
			System.out.println("Files in catalog " + directory + ":");
			for (File file : files) {
				System.out.println((file.isDirectory() ? "[DIR] " : "[FILE]") + file.getName());
			}
		} else {
			System.out.println("Error: Directory does not exist or is not accessible.");
		}
	}

	private static void createFile(String directory, String fileName) {
		File file = new File(directory, fileName);
		try {
			if (file.createNewFile()) {
				System.out.println("File created: " + fileName);
			} else {
				System.out.println("File already exists: " + fileName);
			}
		} catch (IOException e) {
			System.out.println("Error creating file: " + e.getMessage());
		}
	}

	private static void deleteFile(String directory, String fileName) {
		File file = new File(directory, fileName);
		if (file.delete()) {
			System.out.println("File deleted: " + fileName);
		} else {
			System.out.println("Could not delete file");
		}
	}

	private static void readFile(String directory, String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(directory, fileName)))) {
			System.out.println("Content of file " + fileName + ":");
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}

	private static void addTextToFile(String directory, String fileName, String textToAdd) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(directory, fileName), true))) {
			bw.write(textToAdd);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Error adding text to file: " + e.getMessage());
		}
	}

}
