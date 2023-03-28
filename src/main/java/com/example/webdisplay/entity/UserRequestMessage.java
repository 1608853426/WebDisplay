package com.example.webdisplay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestMessage {
    private String RequestId;

    private String ISPId;

    private String IdentId;

    private String TerminalModel;

    private String TimeDelay;

    private String podName;

    private String image;

    private Integer containerPort;

    private String fileNum;

    private String fileSize;

    private String timeStamp;
}
