package com.campus.universe.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.campus.universe.app.data.models.Tutorial;
import com.campus.universe.app.data.repository.TutorialRepository;

import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

/**
 * Tutorial ViewModel
 * Handles tutorial-related UI logic
 */
@HiltViewModel
public class TutorialViewModel extends ViewModel {
    private final TutorialRepository tutorialRepository;

    @Inject
    public TutorialViewModel(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    /**
     * Upload new tutorial
     */
    public void uploadTutorial(Tutorial tutorial) {
        tutorialRepository.uploadTutorial(tutorial);
    }

    /**
     * Load all tutorials
     */
    public void loadTutorials() {
        tutorialRepository.getAllTutorials();
    }

    /**
     * Load tutorials by category
     */
    public void loadTutorialsByCategory(String category) {
        tutorialRepository.getTutorialsByCategory(category);
    }

    /**
     * Observe tutorials
     */
    public LiveData<List<Tutorial>> getTutorials() {
        return tutorialRepository.observeTutorials();
    }

    /**
     * Observe errors
     */
    public LiveData<String> getError() {
        return tutorialRepository.observeError();
    }
}
