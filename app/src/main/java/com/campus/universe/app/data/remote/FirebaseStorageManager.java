package com.campus.universe.app.data.remote;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Firebase Storage Manager
 * Handles file uploads and downloads
 */
@Singleton
public class FirebaseStorageManager {
    private final FirebaseStorage storage;

    @Inject
    public FirebaseStorageManager(FirebaseStorage storage) {
        this.storage = storage;
    }

    /**
     * Upload file to storage
     */
    public void uploadFile(Uri fileUri, String path, StorageCallback callback) {
        StorageReference reference = storage.getReference(path);
        
        reference.putFile(fileUri)
                .addOnSuccessListener(taskSnapshot -> {
                    reference.getDownloadUrl().addOnSuccessListener(uri -> {
                        callback.onSuccess(uri.toString());
                        Timber.d("File uploaded: %s", path);
                    });
                })
                .addOnFailureListener(e -> {
                    callback.onError(e.getMessage());
                    Timber.e(e, "Failed to upload file: %s", path);
                });
    }

    /**
     * Delete file from storage
     */
    public void deleteFile(String path, StorageCallback callback) {
        StorageReference reference = storage.getReference(path);
        
        reference.delete()
                .addOnSuccessListener(v -> {
                    callback.onSuccess(null);
                    Timber.d("File deleted: %s", path);
                })
                .addOnFailureListener(e -> {
                    callback.onError(e.getMessage());
                    Timber.e(e, "Failed to delete file: %s", path);
                });
    }

    /**
     * Get reference to a file
     */
    public StorageReference getReference(String path) {
        return storage.getReference(path);
    }

    /**
     * Storage callback interface
     */
    public interface StorageCallback {
        void onSuccess(String downloadUrl);
        void onError(String error);
    }
}
