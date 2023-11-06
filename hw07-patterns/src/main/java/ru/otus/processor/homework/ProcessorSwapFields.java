package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.Processor;

import java.util.List;
import java.util.stream.Collectors;

public class ProcessorSwapFields implements Processor {
    @Override
    public Message process(Message message) {
        ObjectForMessage field11 = new ObjectForMessage();
        field11.setData(List.of(message.getField11()));
        String field13 = message.getField13() != null ? message.getField13().getData().stream().collect(Collectors.joining(", ")) : null;

        return message.toBuilder().field11(field13).field13(field11).build();
    }
}
