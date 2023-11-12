package ru.otus.javapro.input.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    @JsonProperty("ROWID")
    private int ROWID;
    @JsonProperty("attributedBody")
    private String attributeBody;
    @JsonProperty("belong_number")
    private String belongNumber;
    @JsonProperty("date")
    private DateTime date;
    @JsonProperty("date_read")
    private DateTime dateRead;
    @JsonProperty("guid")
    private UUID guid;
    @JsonProperty("handle_id")
    private int handleId;
    @JsonProperty("has_dd_results")
    private int hasDDResults;
    @JsonProperty("is_deleted")
    private int isDeleted;
    @JsonProperty("is_from_me")
    private int isFromMe;
    @JsonProperty("send_date")
    private String sendDate;
    @JsonProperty("send_status")
    private int sendStatus;
    @JsonProperty("service")
    private String service;
    @JsonProperty("text")
    private String text;
}
