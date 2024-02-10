package it.unicam.cs.ids.localplatform;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unicam.cs.ids.localplatform.model.POI;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class FileService {

    private final ObjectMapper objectMapper;

    public FileService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void saveToFile(POI poi) {
        try {
            Path path = Paths.get("info.txt");

            // If file doesn't exist, create it
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            // Convert POI object to JSON string
            String poiJson = objectMapper.writeValueAsString(poi);

            // Write to file
            Files.write(path, poiJson.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
