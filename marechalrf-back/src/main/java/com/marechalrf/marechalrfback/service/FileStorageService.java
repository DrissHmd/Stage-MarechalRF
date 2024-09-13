package com.marechalrf.marechalrfback.service;

import com.marechalrf.marechalrfback.model.FileStorage;
import com.marechalrf.marechalrfback.repository.FileStorageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    private final FileStorageRepository fileStorageRepository;

    public FileStorageService(@Value("${file.upload-dir}") String uploadDir, FileStorageRepository fileMetadataRepository) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        this.fileStorageRepository = fileMetadataRepository;
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, Long userId) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Invalid path sequence in file name: " + fileName);
            }

            // Enregistrer le fichier sur le disque
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Enregistrer les métadonnées du fichier
            FileStorage metadata = new FileStorage(fileName, file.getContentType(), file.getSize(), userId, new Date());
            fileStorageRepository.save(metadata);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file: " + fileName + ". Please try again!", ex);
        }
    }

    public List<FileStorage> getFilesByUserId(Long userId) {
        return fileStorageRepository.findByUserId(userId);
    }

    public Path getFilePath(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }

}
