package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private static final HashMap<Long, Deque<MessageRecord>> messageStorage = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        Long id = msg.getId();
        MessageRecord record = new MessageRecord(msg.toBuilder().build(), LocalDateTime.now());
        Deque<MessageRecord> records = messageStorage.get(id);
        if (records == null)
            records = new ArrayDeque<>();
        records.push(record);
        messageStorage.put(id, records);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        MessageRecord record = messageStorage.get(id).peek();
        Message msg = record.message();
        return msg == null ? Optional.empty() : Optional.of(msg);
    }
}
