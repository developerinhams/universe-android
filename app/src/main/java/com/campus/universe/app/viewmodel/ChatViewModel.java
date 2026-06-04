package com.campus.universe.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.campus.universe.app.data.models.Message;
import com.campus.universe.app.data.repository.MessageRepository;

import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

/**
 * Chat ViewModel
 * Handles messaging-related UI logic
 */
@HiltViewModel
public class ChatViewModel extends ViewModel {
    private final MessageRepository messageRepository;

    @Inject
    public ChatViewModel(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Send message
     */
    public void sendMessage(Message message) {
        messageRepository.sendMessage(message);
    }

    /**
     * Load messages for conversation
     */
    public void loadMessages(String conversationId) {
        messageRepository.getMessages(conversationId);
    }

    /**
     * Mark message as read
     */
    public void markAsRead(String conversationId, String messageId) {
        messageRepository.markAsRead(conversationId, messageId);
    }

    /**
     * Observe messages
     */
    public LiveData<List<Message>> getMessages() {
        return messageRepository.observeMessages();
    }

    /**
     * Observe errors
     */
    public LiveData<String> getError() {
        return messageRepository.observeError();
    }
}
