package org.example.media.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @NotBlank(message = "mediaId is required")
    private String mediaId;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Text is required")
    private String text;

    private LocalDateTime createdAt = LocalDateTime.now();

    private List<Comment> replies = new ArrayList<>();
}
