package org.example.media.service;

import org.example.media.model.Video;
import org.example.media.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Video findById(String id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));
    }

    public Video create(Video video) {
        return videoRepository.save(video);
    }

    public Video update(String id, Video updated) {
        return videoRepository.findById(id)
                .map(video -> {
                    video.setTitle(updated.getTitle());
                    video.setUrl(updated.getUrl());
                    video.setDuration(updated.getDuration());
                    return videoRepository.save(video);
                })
                .orElseThrow(() -> new RuntimeException("Video not found"));
    }

    public void delete(String id) {
        videoRepository.deleteById(id);
    }
}
