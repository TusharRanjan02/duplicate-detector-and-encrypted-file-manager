package com.fileOrganiser.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    private File logFile;

    public Logger(File rootDirectory) {
        this.logFile = new File(rootDirectory, "organizer_log.txt");
    }

    public void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            String timeStampedMessage = "[" + LocalDateTime.now() + "] " + message;
            writer.write(timeStampedMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write log: " + message);
        }
    }

    public File getLogFile() {
        return logFile;
    }
}
