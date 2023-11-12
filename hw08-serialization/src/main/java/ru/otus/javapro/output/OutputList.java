package ru.otus.javapro.output;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import ru.otus.javapro.input.sms.ChatSession;
import ru.otus.javapro.input.sms.Message;
import ru.otus.javapro.input.sms.Sms;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OutputList {
    private List<OutputRecord> list;

    public OutputList(Sms sms) {
        list = new ArrayList<>();
        fillList(sms);
        formatList();
    }

    private void fillList(Sms sms) {
        ChatSession[] chatSessions = sms.getChatSessions();
        for (ChatSession chatSession : chatSessions) {
            Message[] messages = chatSession.getMessages();
            for (Message message : messages) {
                OutputRecord record = new OutputRecord();
                record.setChatIdentifier(chatSession.getChartIdentifier());
                record.setMembersLast(chatSession.getMembers()[0].getLast());
                record.setMessageBelongNumber(message.getBelongNumber());
                record.setMessageText(message.getText());
                DateTime dateTime = DateTimeFormat.forPattern("MM-dd-yyyy HH:mm:SS")
                        .parseDateTime(message.getSendDate());
                record.setMessageSendDate(dateTime);
                list.add(record);
            }
        }
    }

    private void formatList() {
        Map<String, List<OutputRecord>> listGrouped = list.stream()
                .collect(Collectors.groupingBy(OutputRecord::getMessageBelongNumber, Collectors.toList()));
        list = listGrouped.entrySet().stream()
                .flatMap(e -> e.getValue().stream()
                        .sorted(Comparator.comparing(OutputRecord::getMessageSendDate)))
                .collect(Collectors.toList());
    }

    public OutputList deserializeFromCSV(String path) throws IOException {
        CsvMapper mapper = new CsvMapper();

        CsvSchema csvSchema = mapper.typedSchemaFor(OutputRecord.class).withHeader();
        mapper.registerModule(new JodaModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setTimeZone(TimeZone.getDefault());

        MappingIterator<OutputRecord> outputRecordIter = mapper
                .readerWithTypedSchemaFor(OutputRecord.class)
                .with(csvSchema)
                .readValues(new File(path));

        list = outputRecordIter.readAll();
        return this;
    }

    public void serializeCSVtoFile(String path) throws IOException {
        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("chatIdentifier")
                .addColumn("membersLast")
                .addColumn("messageBelongNumber")
                .addColumn("messageSendDate")
                .addColumn("messageText")
                .build();

        CsvMapper mapper = new CsvMapper();
        mapper.registerModule(new JodaModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setTimeZone(TimeZone.getDefault());

        ObjectWriter writer = mapper.writerFor(OutputRecord.class).with(schema);
        writer.writeValues(new File(path)).writeAll(list);
    }
}
