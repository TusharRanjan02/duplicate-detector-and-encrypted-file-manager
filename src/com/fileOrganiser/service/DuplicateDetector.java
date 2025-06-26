package com.fileOrganiser.service;

import java.io.File;
import java.util.*;

import com.fileOrganiser.model.FileInfo;
import com.fileOrganiser.util.FileUtils;

public class DuplicateDetector {

    // Scans directory and returns a list of duplicate groups
    public List<List<FileInfo>> findDuplicates(File directory) {
        Map<String, List<FileInfo>> fileMap = new HashMap<>();

        List<File> allFiles = FileUtils.getAllFiles(directory);

        for (File file : allFiles) {
            try {
                long size = file.length();
                String hash = FileUtils.getFileHash(file);

                String key = size + "-" + hash;

                FileInfo fileInfo = new FileInfo(file, size, hash);
                fileMap.computeIfAbsent(key, k -> new ArrayList<>()).add(fileInfo);

            } catch (Exception e) {
                System.err.println("Error processing file: " + file.getAbsolutePath());
            }
        }

        // Filter only keys with duplicates
        List<List<FileInfo>> duplicates = new ArrayList<>();
        for (List<FileInfo> group : fileMap.values()) {
            if (group.size() > 1) {
                duplicates.add(group);
            }
        }

        return duplicates;
    }
}
