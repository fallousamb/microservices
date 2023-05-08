package com.mouridedev.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "notification" ,
    url = "${clients.notification.url}",
    path = "api/v1/notifications"
)
public interface NotificationClient {
  @PostMapping
  void sendNotification(@RequestBody NotificationRequest notificationRequest);
}
