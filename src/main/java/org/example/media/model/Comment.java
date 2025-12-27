package org.example.media.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String mediaId;

    private String author;

    private String text;

    private List<Comment> replies = new ArrayList<>();
}
