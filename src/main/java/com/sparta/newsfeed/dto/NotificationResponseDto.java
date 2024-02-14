package com.sparta.newsfeed.dto;

import com.sparta.newsfeed.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {
    private String message;
    private boolean read;

    public NotificationResponseDto(Notification notification){
        this.message = notification.getMessage();
        this.read = notification.isReadStatus();
    }
}
