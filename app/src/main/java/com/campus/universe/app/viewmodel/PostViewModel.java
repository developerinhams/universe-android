package com.campus.universe.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.campus.universe.app.data.models.Post;
import com.campus.universe.app.data.repository.PostRepository;

import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

/**
 * Post/Feed ViewModel
 * Handles post-related UI logic
 */
@HiltViewModel
public class PostViewModel extends ViewModel {
    private final PostRepository postRepository;

    @Inject
    public PostViewModel(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Create new post
     */
    public void createPost(Post post) {
        postRepository.createPost(post);
    }

    /**
     * Delete post
     */
    public void deletePost(String postId) {
        postRepository.deletePost(postId);
    }

    /**
     * Like post
     */
    public void likePost(String postId, String userId) {
        postRepository.likePost(postId, userId);
    }

    /**
     * Unlike post
     */
    public void unlikePost(String postId, String userId) {
        postRepository.unlikePost(postId, userId);
    }

    /**
     * Load all posts
     */
    public void loadPosts() {
        postRepository.getAllPosts();
    }

    /**
     * Observe posts
     */
    public LiveData<List<Post>> getPosts() {
        return postRepository.observePosts();
    }

    /**
     * Observe errors
     */
    public LiveData<String> getError() {
        return postRepository.observeError();
    }
}
