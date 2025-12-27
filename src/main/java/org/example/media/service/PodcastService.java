package org.example.media.service;

import org.example.media.model.Podcast;
import org.example.media.repository.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodcastService {

    @Autowired
    private PodcastRepository podcastRepository;

    public List<Podcast> findAll() {
        return podcastRepository.findAll();
    }

    public Podcast findById(String id) {
        return podcastRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Podcast not found"));
    }

    public Podcast create(Podcast podcast) {
        return podcastRepository.save(podcast);
    }

    public Podcast update(String id, Podcast updated) {
        return podcastRepository.findById(id)
                .map(podcast -> {
                    podcast.setTitle(updated.getTitle());
                    podcast.setHost(updated.getHost());
                    podcast.setDescription(updated.getDescription());
                    return podcastRepository.save(podcast);
                })
                .orElseThrow(() -> new RuntimeException("Podcast not found"));
    }

    public void delete(String id) {
        podcastRepository.deleteById(id);
    }
}
