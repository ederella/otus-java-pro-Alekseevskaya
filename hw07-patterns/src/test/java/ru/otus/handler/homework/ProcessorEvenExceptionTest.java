package ru.otus.handler.homework;

import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import ru.otus.model.Message;
import ru.otus.processor.homework.EvenException;
import ru.otus.processor.homework.ProcessorEvenException;

import java.util.Date;


class ProcessorEvenExceptionTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    @DisplayName("Тестируем процессор")
    void evenExceptionProcessorTest() throws Exception {
        var message = new Message.Builder(1L).field7("field7").build();

        ProcessorEvenException processor = new ProcessorEvenException();
        exception.expect(EvenException.class);
        if (new Date().getTime() % 2000 != 0)
            Thread.sleep(1000);
        processor.process(message);
    }
}