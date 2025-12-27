package org.example.media.repository;


import org.example.media.model.MediaContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MediaContentRepository extends MongoRepository<MediaContent, String> {

}