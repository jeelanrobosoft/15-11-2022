package com.robosoft.virtualLearn.Menu.dto;


import lombok.Data;

import java.util.Date;

@Data
public class NotificationResponse {
    private String description;
    private String timeStamp;
    private String notificationUrl;
}
