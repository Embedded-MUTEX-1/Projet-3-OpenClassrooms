package fr.lenny.backend.service;

import fr.lenny.backend.exception.FileEmptyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${resources.url}")
    String resourcesUrl;

    @Override
    public String store(MultipartFile file, String name) throws IOException {
        if(file.isEmpty()) {
            throw new FileEmptyException();
        }
        String fullName = name + file.getOriginalFilename();
        Path destination = Paths.get("src/main/resources/static/images").resolve(fullName).normalize().toAbsolutePath();
        Files.copy(file.getInputStream(), destination);
        return resourcesUrl + fullName;
    }
}
