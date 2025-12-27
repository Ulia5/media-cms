package org.example.media.service;

import org.example.media.model.Article;
import org.example.media.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(String id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public Article update(String id, Article updated) {
        return articleRepository.findById(id)
                .map(article -> {
                    article.setTitle(updated.getTitle());
                    article.setContent(updated.getContent());
                    article.setAuthor(updated.getAuthor());
                    return articleRepository.save(article);
                })
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public void delete(String id) {
        articleRepository.deleteById(id);
    }
}
