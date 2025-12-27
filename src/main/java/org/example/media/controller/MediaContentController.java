package org.example.media.controller;


import org.example.media.model.MediaContent;
import org.example.media.service.MediaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/media")
public class MediaContentController {


    private final MediaContentService mediaContentService;


    @Autowired
    public MediaContentController(MediaContentService mediaContentService) {
        this.mediaContentService = mediaContentService;
    }


    @PostMapping
    public MediaContent createMedia(@RequestBody @Valid MediaContent mediaContent) {
        return mediaContentService.createMedia(mediaContent);
    }


    @GetMapping
    public List<MediaContent> getAllMedia() {
        return mediaContentService.getAllMedia();
    }


    @GetMapping("/{id}")
    public MediaContent getMediaById(@PathVariable String id) {
        return mediaContentService.getMediaById(id);
    }


    @PutMapping("/{id}")
    public MediaContent updateMedia(@PathVariable String id, @RequestBody @Valid MediaContent mediaContent) {
        return mediaContentService.updateMedia(id, mediaContent);
    }


    @DeleteMapping("/{id}")
    public void deleteMedia(@PathVariable String id) {
        mediaContentService.deleteMedia(id);
    }
}
