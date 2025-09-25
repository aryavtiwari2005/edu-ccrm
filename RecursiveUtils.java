package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;


public class RecursiveUtils {

    
    public static long calculateDirectorySize(Path path) throws IOException {
        if (!Files.exists(path)) {
            return 0;
        }

        
        if (Files.isRegularFile(path)) {
            return Files.size(path);
        }

        
        long size = 0;
        try (Stream<Path> stream = Files.list(path)) {
            
            size = stream.mapToLong(p -> {
                try {
                    return calculateDirectorySize(p);
                } catch (IOException e) {
                    System.err.println("Failed to read size of: " + p);
                    return 0L;
                }
            }).sum();
        }
        return size;
    }
}