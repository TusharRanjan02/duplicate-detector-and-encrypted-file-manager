package com.fileOrganiser.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class FileOrganizer {

    // Define file type categories
    private static final Map<String, String> typeMap = new HashMap<>();

    static {
        // Images
        typeMap.put("jpg", "Images");
        typeMap.put("jpeg", "Images");
        typeMap.put("png", "Images");
        typeMap.put("gif", "Images");
        typeMap.put("bmp", "Images");

        // Documents
        typeMap.put("pdf", "Documents");
        typeMap.put("doc", "Documents");
        typeMap.put("docx", "Documents");
        typeMap.put("txt", "Documents");
        typeMap.put("ppt", "Documents");
        typeMap.put("pptx", "Documents");

        // Videos
        typeMap.put("mp4", "Videos");
        typeMap.put("avi", "Videos");
        typeMap.put("mov", "Videos");
        typeMap.put("mkv", "Videos");

        // Audio
        typeMap.put("mp3", "Audio");
        typeMap.put("wav", "Audio");

        // Compressed
        typeMap.put("zip", "Compressed");
        typeMap.put("rar", "Compressed");

        // Others
        // Default to "Others"
    }

    public void organizeFiles(File directory) {
        if (!directory.isDirectory()) {
            System.out.println("Provided path is not a directory.");
            return;
        }

        File[] files = directory.listFiles();
        if (files == null)
            return;

        for (File file : files) {
            if (file.isFile()) {
                String extension = getFileExtension(file);
                String folderName = typeMap.getOrDefault(extension, "Others");
                File newDir = new File(directory, folderName);

                if (!newDir.exists()) {
                    newDir.mkdir();
                }

                try {
                    Files.move(
                            file.toPath(),
                            new File(newDir, file.getName()).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Moved " + file.getName() + " → " + folderName);
                } catch (IOException e) {
                    System.err.println("Error moving file: " + file.getName());
                }
            }
        }
    }

    // Helper method to get extension
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastDot = name.lastIndexOf('.');
        if (lastDot == -1 || lastDot == name.length() - 1)
            return "";
        return name.substring(lastDot + 1).toLowerCase();
    }
}
