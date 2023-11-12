package ru.otus.javapro.input.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
public class Sms {
    @JsonProperty("chat_sessions")
    private ChatSession[] chatSessions;

    public static Sms deserializeFromFile(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        return objectMapper.readValue(new File(path), Sms.class);
    }
}
