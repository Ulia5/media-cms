package org.example.media.controller;

import org.example.media.model.Article;
import org.example.media.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAll() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getById(@PathVariable String id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Article> create(@Valid @RequestBody Article article) {
        return ResponseEntity.ok(articleService.create(article));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable String id, @Valid @RequestBody Article updated) {
        return ResponseEntity.ok(articleService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

