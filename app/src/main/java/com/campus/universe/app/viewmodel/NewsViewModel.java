package com.campus.universe.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.campus.universe.app.data.models.News;
import com.campus.universe.app.data.repository.NewsRepository;

import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

/**
 * News ViewModel
 * Handles news-related UI logic
 */
@HiltViewModel
public class NewsViewModel extends ViewModel {
    private final NewsRepository newsRepository;

    @Inject
    public NewsViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * Post news (admin only)
     */
    public void postNews(News news) {
        newsRepository.postNews(news);
    }

    /**
     * Load all news
     */
    public void loadNews() {
        newsRepository.getAllNews();
    }

    /**
     * Load breaking news only
     */
    public void loadBreakingNews() {
        newsRepository.getBreakingNews();
    }

    /**
     * Observe news
     */
    public LiveData<List<News>> getNews() {
        return newsRepository.observeNews();
    }

    /**
     * Observe errors
     */
    public LiveData<String> getError() {
        return newsRepository.observeError();
    }
}
