package com.example.webdisplay.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class StepThreeMessage {
    Integer step;
    String requestId;
    String url;

    String timeStamp;
}
