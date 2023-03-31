package com.example.webdisplay.constants;

public interface KafkaConsts {
    /**
     * 默认分区大小
     */
    Integer DEFAULT_PARTITION_NUM = 1;

    String TOPIC_REQ = "user_request";

    /**
     * Topic 名称
     */
    String TOPIC_RESULT = "place_result";

    String TOPIC_COMMAND = "node_command";

}
