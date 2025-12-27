package org.example.media.controller;

import org.example.media.model.Video;
import org.example.media.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping
    public List<Video> getAll() {
        return videoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getById(@PathVariable String id) {
        return ResponseEntity.ok(videoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Video> create(@Valid @RequestBody Video video) {
        return ResponseEntity.ok(videoService.create(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> update(@PathVariable String id, @Valid @RequestBody Video updated) {
        return ResponseEntity.ok(videoService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        videoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
