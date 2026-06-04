package com.campus.universe.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.campus.universe.app.data.models.Message;
import com.campus.universe.app.data.remote.FirebaseDatabaseManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Message Repository
 * Handles direct message operations
 */
@Singleton
public class MessageRepository {
    private final FirebaseDatabaseManager databaseManager;
    private final MutableLiveData<List<Message>> messages = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public MessageRepository(FirebaseDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Send message
     */
    public void sendMessage(Message message) {
        String messageId = databaseManager.getMessagesReference(message.conversationId).push().getKey();
        if (messageId != null) {
            message.messageId = messageId;
            databaseManager.writeData(
                    "messages/" + message.conversationId + "/" + messageId,
                    message,
                    new FirebaseDatabaseManager.DatabaseCallback() {
                        @Override
                        public void onSuccess() {
                            Timber.d("Message sent: %s", messageId);
                        }

                        @Override
                        public void onError(String errorMessage) {
                            error.setValue(errorMessage);
                            Timber.e("Failed to send message: %s", errorMessage);
                        }
                    }
            );
        }
    }

    /**
     * Get messages for conversation
     */
    public void getMessages(String conversationId) {
        databaseManager.getMessagesReference(conversationId)
                .orderByChild("timestamp")
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<Message> messageList = new ArrayList<>();
                    for (var child : snapshot.getChildren()) {
                        Message message = child.getValue(Message.class);
                        if (message != null) {
                            messageList.add(message);
                        }
                    }
                    messages.setValue(messageList);
                    Timber.d("Messages loaded: %d", messageList.size());
                })
                .addOnFailureListener(e -> {
                    error.setValue(e.getMessage());
                    Timber.e(e, "Failed to load messages");
                });
    }

    /**
     * Mark message as read
     */
    public void markAsRead(String conversationId, String messageId) {
        databaseManager.getMessagesReference(conversationId)
                .child(messageId)
                .child("isRead")
                .setValue(true);
        Timber.d("Message marked as read: %s", messageId);
    }

    /**
     * Observe messages
     */
    public LiveData<List<Message>> observeMessages() {
        return messages;
    }

    /**
     * Observe errors
     */
    public LiveData<String> observeError() {
        return error;
    }
}
