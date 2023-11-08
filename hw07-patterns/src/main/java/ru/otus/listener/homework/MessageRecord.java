package ru.otus.listener.homework;

import ru.otus.model.Message;

import java.time.LocalDateTime;

record MessageRecord(Message message, LocalDateTime createdAt) {
    MessageRecord(Message message, LocalDateTime createdAt) {
        this.message = message;
        this.createdAt = createdAt;
    }
}
