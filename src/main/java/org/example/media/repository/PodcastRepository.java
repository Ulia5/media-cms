package org.example.media.repository;

import org.example.media.model.Podcast;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PodcastRepository extends MongoRepository<Podcast, String> {
}
