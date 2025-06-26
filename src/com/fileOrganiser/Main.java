package com.fileOrganiser;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.fileOrganiser.model.FileInfo;
import com.fileOrganiser.service.DuplicateDetector;
import com.fileOrganiser.service.Encryptor;
import com.fileOrganiser.service.FileOrganizer;
import com.fileOrganiser.service.Logger;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("📁 Enter the path of the folder to manage:");
        String path = scanner.nextLine();
        File folder = new File(path);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("❌ Invalid directory path. Exiting.");
            return;
        }

        DuplicateDetector detector = new DuplicateDetector();
        FileOrganizer organizer = new FileOrganizer();
        Encryptor encryptor = new Encryptor();
        Logger logger = new Logger(folder);

        while (true) {
            System.out.println("\n========== File Organizer Menu ==========");
            System.out.println("1. Detect & Clean Duplicate Files");
            System.out.println("2. Organise Files by Type");
            System.out.println("3. Encrypt a File");
            System.out.println("4. Decrypt a File");
            System.out.println("5. View Log File Path");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1–6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("🔍 Scanning for duplicates...");
                    List<List<FileInfo>> duplicates = detector.findDuplicates(folder);

                    if (duplicates.isEmpty()) {
                        System.out.println("✅ No duplicate files found.");
                    } else {
                        int groupNum = 1;
                        for (List<FileInfo> group : duplicates) {
                            System.out.println("\nGroup " + groupNum + " duplicates:");
                            for (int i = 0; i < group.size(); i++) {
                                System.out.println("  [" + i + "] " + group.get(i).getFile().getAbsolutePath());
                            }

                            System.out.print("Delete all except file 0? (y/n): ");
                            String delChoice = scanner.nextLine();
                            if (delChoice.equalsIgnoreCase("y")) {
                                for (int i = 1; i < group.size(); i++) {
                                    File fileToDelete = group.get(i).getFile();
                                    if (fileToDelete.delete()) {
                                        logger.log("Deleted duplicate file: " + fileToDelete.getAbsolutePath());
                                        System.out.println("Deleted: " + fileToDelete.getName());
                                    } else {
                                        System.out.println("Failed to delete: " + fileToDelete.getName());
                                    }
                                }
                            }
                            groupNum++;
                        }
                    }
                    break;

                case 2:
                    organizer.organizeFiles(folder);
                    logger.log("Organized files by type.");
                    break;

                case 3:
                    System.out.print("Enter file path to encrypt: ");
                    String encryptPath = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String encPassword = scanner.nextLine();

                    try {
                        File input = new File(encryptPath);
                        File output = new File(encryptPath + ".enc");
                        encryptor.encrypt(input, output, encPassword);
                        logger.log("Encrypted file: " + input.getAbsolutePath());
                        System.out.println("✅ File encrypted successfully.");
                    } catch (Exception e) {
                        System.err.println("❌ Encryption failed: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter encrypted file path to decrypt: ");
                    String decryptPath = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String decPassword = scanner.nextLine();

                    try {
                        File input = new File(decryptPath);
                        String originalName = decryptPath.replaceFirst("\\.enc$", "");
                        File output = new File(originalName + "_decrypted");
                        encryptor.decrypt(input, output, decPassword);
                        logger.log("Decrypted file: " + input.getAbsolutePath());
                        System.out.println("✅ File decrypted successfully.");
                    } catch (Exception e) {
                        System.err.println("❌ Decryption failed: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("📝 Log file: " + logger.getLogFile().getAbsolutePath());
                    break;

                case 6:
                    System.out.println("👋 Exiting. Goodbye!");
                    return;

                default:
                    System.out.println("❗ Invalid choice. Please try again.");
            }
        }
    }
}
