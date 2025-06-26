package com.fileOrganiser.util;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    // Recursively gets all files in a directory
    public static List<File> getAllFiles(File directory) {
        List<File> fileList = new ArrayList<>();
        fetchFilesRecursively(directory, fileList);
        return fileList;
    }

    private static void fetchFilesRecursively(File dir, List<File> fileList) {
        File[] files = dir.listFiles();
        if (files == null)
            return;

        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                fetchFilesRecursively(file, fileList);
            }
        }
    }

    // Computes SHA-256 hash of a file
    public static String getFileHash(File file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[8192];
        int read;

        while ((read = fis.read(buffer)) != -1) {
            digest.update(buffer, 0, read);
        }
        fis.close();

        byte[] hashBytes = digest.digest();
        return bytesToHex(hashBytes);
    }

    // Converts byte[] to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
