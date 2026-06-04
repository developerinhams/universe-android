package com.campus.universe.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.campus.universe.app.data.models.Post;
import com.campus.universe.app.data.remote.FirebaseDatabaseManager;
import com.campus.universe.app.data.remote.FirebaseStorageManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Post Repository
 * Handles post data operations
 */
@Singleton
public class PostRepository {
    private final FirebaseDatabaseManager databaseManager;
    private final FirebaseStorageManager storageManager;
    private final MutableLiveData<List<Post>> posts = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public PostRepository(FirebaseDatabaseManager databaseManager, FirebaseStorageManager storageManager) {
        this.databaseManager = databaseManager;
        this.storageManager = storageManager;
    }

    /**
     * Create new post
     */
    public void createPost(Post post) {
        String postId = databaseManager.getPostsReference().push().getKey();
        if (postId != null) {
            post.postId = postId;
            databaseManager.writeData("posts/" + postId, post, new FirebaseDatabaseManager.DatabaseCallback() {
                @Override
                public void onSuccess() {
                    Timber.d("Post created: %s", postId);
                }

                @Override
                public void onError(String errorMessage) {
                    error.setValue(errorMessage);
                    Timber.e("Failed to create post: %s", errorMessage);
                }
            });
        }
    }

    /**
     * Delete post
     */
    public void deletePost(String postId) {
        databaseManager.deleteData("posts/" + postId, new FirebaseDatabaseManager.DatabaseCallback() {
            @Override
            public void onSuccess() {
                Timber.d("Post deleted: %s", postId);
            }

            @Override
            public void onError(String errorMessage) {
                error.setValue(errorMessage);
                Timber.e("Failed to delete post: %s", errorMessage);
            }
        });
    }

    /**
     * Like post
     */
    public void likePost(String postId, String userId) {
        databaseManager.getPostReference(postId).child("likes").child(userId).setValue(true);
        Timber.d("Post %s liked by %s", postId, userId);
    }

    /**
     * Unlike post
     */
    public void unlikePost(String postId, String userId) {
        databaseManager.getPostReference(postId).child("likes").child(userId).removeValue();
        Timber.d("Post %s unliked by %s", postId, userId);
    }

    /**
     * Get all posts
     */
    public void getAllPosts() {
        databaseManager.getPostsReference()
                .orderByChild("timestamp")
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<Post> postList = new ArrayList<>();
                    for (var child : snapshot.getChildren()) {
                        Post post = child.getValue(Post.class);
                        if (post != null) {
                            postList.add(0, post); // Add to beginning for reverse order
                        }
                    }
                    posts.setValue(postList);
                    Timber.d("Posts loaded: %d", postList.size());
                })
                .addOnFailureListener(e -> {
                    error.setValue(e.getMessage());
                    Timber.e(e, "Failed to load posts");
                });
    }

    /**
     * Observe posts
     */
    public LiveData<List<Post>> observePosts() {
        return posts;
    }

    /**
     * Observe errors
     */
    public LiveData<String> observeError() {
        return error;
    }
}
