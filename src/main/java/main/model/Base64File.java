package main.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Base64File {
    private Long id;
    private String base64Data;
    private String title;
    private LocalDateTime creationDate;
    private String description;
}
