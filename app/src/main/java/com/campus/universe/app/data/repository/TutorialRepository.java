package com.campus.universe.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.campus.universe.app.data.models.Tutorial;
import com.campus.universe.app.data.remote.FirebaseDatabaseManager;
import com.campus.universe.app.data.remote.FirebaseStorageManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Tutorial Repository
 * Handles tutorial data operations
 */
@Singleton
public class TutorialRepository {
    private final FirebaseDatabaseManager databaseManager;
    private final FirebaseStorageManager storageManager;
    private final MutableLiveData<List<Tutorial>> tutorials = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public TutorialRepository(FirebaseDatabaseManager databaseManager, FirebaseStorageManager storageManager) {
        this.databaseManager = databaseManager;
        this.storageManager = storageManager;
    }

    /**
     * Upload tutorial
     */
    public void uploadTutorial(Tutorial tutorial) {
        String tutorialId = databaseManager.getTutorialsReference().push().getKey();
        if (tutorialId != null) {
            tutorial.tutorialId = tutorialId;
            databaseManager.writeData("tutorials/" + tutorialId, tutorial, new FirebaseDatabaseManager.DatabaseCallback() {
                @Override
                public void onSuccess() {
                    Timber.d("Tutorial uploaded: %s", tutorialId);
                }

                @Override
                public void onError(String errorMessage) {
                    error.setValue(errorMessage);
                    Timber.e("Failed to upload tutorial: %s", errorMessage);
                }
            });
        }
    }

    /**
     * Get all tutorials
     */
    public void getAllTutorials() {
        databaseManager.getTutorialsReference()
                .orderByChild("timestamp")
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<Tutorial> tutorialList = new ArrayList<>();
                    for (var child : snapshot.getChildren()) {
                        Tutorial tutorial = child.getValue(Tutorial.class);
                        if (tutorial != null) {
                            tutorialList.add(0, tutorial);
                        }
                    }
                    tutorials.setValue(tutorialList);
                    Timber.d("Tutorials loaded: %d", tutorialList.size());
                })
                .addOnFailureListener(e -> {
                    error.setValue(e.getMessage());
                    Timber.e(e, "Failed to load tutorials");
                });
    }

    /**
     * Get tutorials by category
     */
    public void getTutorialsByCategory(String category) {
        databaseManager.getTutorialsReference()
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<Tutorial> tutorialList = new ArrayList<>();
                    for (var child : snapshot.getChildren()) {
                        Tutorial tutorial = child.getValue(Tutorial.class);
                        if (tutorial != null && category.equals(tutorial.category)) {
                            tutorialList.add(tutorial);
                        }
                    }
                    tutorials.setValue(tutorialList);
                    Timber.d("Tutorials loaded for category %s: %d", category, tutorialList.size());
                })
                .addOnFailureListener(e -> {
                    error.setValue(e.getMessage());
                    Timber.e(e, "Failed to load tutorials for category: %s", category);
                });
    }

    /**
     * Observe tutorials
     */
    public LiveData<List<Tutorial>> observeTutorials() {
        return tutorials;
    }

    /**
     * Observe errors
     */
    public LiveData<String> observeError() {
        return error;
    }
}
