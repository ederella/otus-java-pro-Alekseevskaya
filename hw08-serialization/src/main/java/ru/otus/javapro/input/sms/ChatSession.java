package ru.otus.javapro.input.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ChatSession {
    @JsonProperty("chat_id")
    private int chatId;
    @JsonProperty("chat_identifier")
    private String chartIdentifier;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("is_deleted")
    private int isDeleted;
    @JsonProperty("members")
    private Member[] members;
    @JsonProperty("messages")
    private Message[] messages;
}
