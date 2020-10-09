package com.card.command.card;

import lombok.Data;

@Data
public class CardFindCommand {
    private String content;
    private Boolean state;
    private Long startTime;
    private Long endTime;
}
