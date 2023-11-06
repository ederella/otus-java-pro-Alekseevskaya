package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.util.Date;

public class ProcessorEvenException implements Processor {
    @Override
    public Message process(Message message) throws Exception {
        if (new Date().getTime() % 2000 == 0)
            throw new EvenException();
        return message;
    }
}
