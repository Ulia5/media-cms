package org.example.media.controller;

import org.example.media.model.Podcast;
import org.example.media.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/podcasts")
public class PodcastController {

    @Autowired
    private PodcastService podcastService;

    @GetMapping
    public List<Podcast> getAll() {
        return podcastService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Podcast> getById(@PathVariable String id) {
        return ResponseEntity.ok(podcastService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Podcast> create(@Valid @RequestBody Podcast podcast) {
        return ResponseEntity.ok(podcastService.create(podcast));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Podcast> update(@PathVariable String id, @Valid @RequestBody Podcast updated) {
        return ResponseEntity.ok(podcastService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        podcastService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
