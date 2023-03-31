package com.example.webdisplay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StepTwoMessage {
    Integer step;
    String requestId;

    String node;

    String timeStamp;

    String cpuSize;

    String memorySize;

    String terminalModel;

    String state;
}
