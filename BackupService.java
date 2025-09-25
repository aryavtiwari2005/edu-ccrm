package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;


public class BackupService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    
    public void createBackup(Path sourceDir, Path backupRootDir) throws IOException {
        if (!Files.exists(sourceDir) || !Files.isDirectory(sourceDir)) {
            System.err.println("Source directory does not exist or is not a directory: " + sourceDir);
            return;
        }

        
        String timestamp = LocalDateTime.now().format(formatter);
        Path backupTargetDir = backupRootDir.resolve("backup_" + timestamp);

        
        Files.createDirectories(backupTargetDir);
        System.out.println("Created backup directory: " + backupTargetDir);

        
        try (Stream<Path> paths = Files.walk(sourceDir)) {
            paths
                .filter(Files::isRegularFile)
                .forEach(sourceFile -> {
                    try {
                        
                        Path destinationFile = backupTargetDir.resolve(sourceDir.relativize(sourceFile));
                        
                        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Failed to copy file: " + sourceFile + " - " + e.getMessage());
                    }
                });
        }
        System.out.println("Backup completed successfully.");
    }
}