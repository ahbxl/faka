package com.card.command.exportfile;

import lombok.Data;

@Data
public class ExportFileCommand {
    private String fileName;
    private Long startTime;
    private Long endTime;
}
