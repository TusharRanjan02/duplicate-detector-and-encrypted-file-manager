package com.fileOrganiser.model;

import java.io.File;

public class FileInfo {
    private File file;
    private long size;
    private String hash;

    public FileInfo(File file, long size, String hash) {
        this.file = file;
        this.size = size;
        this.hash = hash;
    }

    public File getFile() {
        return file;
    }

    public long getSize() {
        return size;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "file=" + file.getAbsolutePath() +
                ", size=" + size +
                ", hash='" + hash + '\'' +
                '}';
    }
}
