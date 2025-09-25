package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ImportExportService {

    
    public void exportStudents(List<Student> students, Path filePath) throws IOException {
        
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            
            writer.write("ID,RegNo,FullName,Email,Status,DateOfBirth\n");
            
            for (Student s : students) {
                String line = String.join(",",
                        String.valueOf(s.getId()),
                        s.getRegNo(),
                        s.getFullName(),
                        s.getEmail(),
                        s.getStatus().name(),
                        s.getDateOfBirth().toString());
                writer.write(line);
                writer.newLine();
            }
        }
    }

    
    public List<Student> importStudents(Path filePath) throws IOException {
        
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines
                    .skip(1) 
                    .map(line -> {
                        String[] parts = line.split(",");
                        
                        int id = Integer.parseInt(parts[0]);
                        String regNo = parts[1];
                        String fullName = parts[2];
                        String email = parts[3];
                        
                        LocalDate dob = LocalDate.parse(parts[5]);
                        return new Student(id, fullName, email, dob, regNo);
                    })
                    .collect(Collectors.toList());
        }
    }
}