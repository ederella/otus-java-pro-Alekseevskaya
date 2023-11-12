package ru.otus.javapro.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OutputRecord {
    private String chatIdentifier;
    private String membersLast;
    private String messageBelongNumber;
    private DateTime messageSendDate;
    private String messageText;
}
