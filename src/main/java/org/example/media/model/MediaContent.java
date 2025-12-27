package org.example.media.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotBlank;


@Data
@Document(collection = "media")
public class MediaContent {


    @Id
    private String id;


    @NotBlank(message = "Title is required")
    private String title;


    private String description;


    @NotBlank(message = "URL is required")
    private String url;


    private String type;
}