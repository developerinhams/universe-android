package com.campus.universe.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.campus.universe.app.data.models.News;
import com.campus.universe.app.data.remote.FirebaseDatabaseManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * News Repository
 * Handles university news operations
 */
@Singleton
public class NewsRepository {
    private final FirebaseDatabaseManager databaseManager;
    private final MutableLiveData<List<News>> news = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public NewsRepository(FirebaseDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Post news (admin only)
     */
    public void postNews(News newsItem) {
        String newsId = databaseManager.getNewsReference().push().getKey();
        if (newsId != null) {
            newsItem.newsId = newsId;
            databaseManager.writeData("news/" + newsId, newsItem, new FirebaseDatabaseManager.DatabaseCallback() {
                @Override
                public void onSuccess() {
                    Timber.d("News posted: %s", newsId);
                }

                @Override
                public void onError(String errorMessage) {
                    error.setValue(errorMessage);
                    Timber.e("Failed to post news: %s", errorMessage);
                }
            });
        }
    }

    /**
     * Get all news
     */
    public void getAllNews() {
        databaseManager.getNewsReference()
                .orderByChild("timestamp")
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<News> newsList = new ArrayList<>();
                    for (var child : snapshot.getChildren()) {
                        News newsItem = child.getValue(News.class);
                        if (newsItem != null) {
                            newsList.add(0, newsItem);
                        }
                    }
                    news.setValue(newsList);
                    Timber.d("News loaded: %d", newsList.size());
                })
                .addOnFailureListener(e -> {
                    error.setValue(e.getMessage());
                    Timber.e(e, "Failed to load news");
                });
    }

    /**
     * Get breaking news
     */
    public void getBreakingNews() {
        databaseManager.getNewsReference()
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<News> breakingNewsList = new ArrayList<>();
                    for (var child : snapshot.getChildren()) {
                        News newsItem = child.getValue(News.class);
                        if (newsItem != null && newsItem.isBreaking) {
                            breakingNewsList.add(newsItem);
                        }
                    }
                    news.setValue(breakingNewsList);
                    Timber.d("Breaking news loaded: %d", breakingNewsList.size());
                })
                .addOnFailureListener(e -> {
                    error.setValue(e.getMessage());
                    Timber.e(e, "Failed to load breaking news");
                });
    }

    /**
     * Observe news
     */
    public LiveData<List<News>> observeNews() {
        return news;
    }

    /**
     * Observe errors
     */
    public LiveData<String> observeError() {
        return error;
    }
}
