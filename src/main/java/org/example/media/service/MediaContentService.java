package org.example.media.service;


import org.example.media.model.MediaContent;
import org.example.media.repository.MediaContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class MediaContentService {


    private final MediaContentRepository mediaContentRepository;


    @Autowired
    public MediaContentService(MediaContentRepository mediaContentRepository) {
        this.mediaContentRepository = mediaContentRepository;
    }


    public MediaContent createMedia(MediaContent mediaContent) {
        return mediaContentRepository.save(mediaContent);
    }


    public List<MediaContent> getAllMedia() {
        return mediaContentRepository.findAll();
    }


    public MediaContent getMediaById(String id) {
        return mediaContentRepository.findById(id).orElse(null);
    }


    public void deleteMedia(String id) {
        mediaContentRepository.deleteById(id);
    }


    public MediaContent updateMedia(String id, MediaContent updatedContent) {
        return mediaContentRepository.findById(id).map(existing -> {
            existing.setTitle(updatedContent.getTitle());
            existing.setDescription(updatedContent.getDescription());
            existing.setUrl(updatedContent.getUrl());
            existing.setType(updatedContent.getType());
            return mediaContentRepository.save(existing);
        }).orElse(null);
    }
}

