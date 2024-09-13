package com.marechalrf.marechalrfback.model;

import jakarta.persistence.Id;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "files")
public class FileStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;
    private Long fileSize;
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    public FileStorage(String fileName, String fileType, Long fileSize, Long userId, Date uploadDate) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.uploadDate = uploadDate;
    }
}
